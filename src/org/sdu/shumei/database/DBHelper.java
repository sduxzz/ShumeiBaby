package org.sdu.shumei.database;

import org.sdu.shumei.database.model.Progress;
import org.sdu.shumei.database.model.Prop;

import android.content.Context;

import com.tgb.lk.ahibernate.util.MyDBHelper;

/**
 * �������ݿ�
 * 
 * @author Craig Lee
 * 
 */
public class DBHelper extends MyDBHelper {
	private static final String DBNAME = "shumei.db";// ���ݿ���
	private static final int DBVERSION = 1;
	private static final Class<?>[] clazz = { Prop.class, Progress.class };// Ҫ��ʼ���ı�

	public DBHelper(Context context) {
		super(context, DBNAME, null, DBVERSION, clazz);
	}

}
