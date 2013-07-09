package org.sdu.shumei.database.dao;

import java.util.List;

import org.sdu.shumei.database.DBHelper;
import org.sdu.shumei.database.model.Prop;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class PropDAO extends BaseDaoImpl<Prop> {

	public PropDAO(Context context) {
		super(new DBHelper(context));
	}

	/**
	 * ����߻��Ļ���
	 * 
	 * @return
	 */
	public int getScoreConsumed() {
		int sum = 0;
		List<Prop> props = this.find();
		for (Prop p : props) {
			sum += p.getScoreConsumed();
		}
		return sum;
	}

	/**
	 * �ý�ɫ�����Ƿ񱻹���
	 * 
	 * @param roleId
	 * @return
	 */
	public boolean isBought(int roleId) {
		List<Prop> props = this
				.find(null, "propId = ?",
						new String[] { String.valueOf(roleId) }, null, null,
						null, null);
		if (props.size() > 0)
			return true;
		return false;
	}

}
