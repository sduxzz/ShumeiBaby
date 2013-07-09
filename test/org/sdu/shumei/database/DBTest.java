package org.sdu.shumei.database;

import org.sdu.shumei.Const;
import org.sdu.shumei.database.dao.ProgressDAO;
import org.sdu.shumei.database.dao.PropDAO;
import org.sdu.shumei.database.model.Progress;
import org.sdu.shumei.database.model.Prop;
import org.sdu.shumei.utils.L;

import android.test.AndroidTestCase;

/**
 * Êý¾Ý¿â²âÊÔ
 * 
 * @author Craig Lee
 * 
 */
public class DBTest extends AndroidTestCase {

	public void testProgressDAO() {
		ProgressDAO progressDAO = new ProgressDAO(getContext());
		Progress pro1 = new Progress();
		pro1.setLevel(1);
		pro1.setSection(1);
		pro1.setScore(2000);
		pro1.setStar(3);
		pro1.setStatus(Const.LevelStatus.UNLOCK_PASS);
		Long pro1Id = progressDAO.insert(pro1);
		Progress pro1a = progressDAO.get(pro1Id.intValue());
		L.i(pro1a);

		Progress pro2 = new Progress();
		pro2.setLevel(2);
		pro2.setSection(1);
		pro2.setStar(1);
		pro2.setStatus(Const.LevelStatus.UNLOCK_NOT_PASS);
		pro2.setScore(100);
		progressDAO.insert(pro2);

	}

	public void testPropDAO() {
		PropDAO propDAO = new PropDAO(getContext());
		ProgressDAO progressDAO = new ProgressDAO(getContext());

		Prop prop1 = new Prop();
		prop1.setPropId(1);
		prop1.setScoreConsumed(900);
		propDAO.insert(prop1);

		int scoreConsumed = propDAO.getScoreConsumed();
		int scoreSum = progressDAO.getScoreEarned();
		L.i("left score:" + (scoreSum - scoreConsumed));

	}

}
