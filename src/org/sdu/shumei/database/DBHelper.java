package org.sdu.shumei.database;

import org.sdu.shumei.database.model.Progress;
import org.sdu.shumei.database.model.Prop;

import android.content.Context;

import com.tgb.lk.ahibernate.util.MyDBHelper;

/**
 * 创建数据库
 * 
 * @author Craig Lee
 * 
 */
public class DBHelper extends MyDBHelper {
	private static final String DBNAME = "shumei.db";// 数据库名
	private static final int DBVERSION = 1;
	private static final Class<?>[] clazz = { Prop.class, Progress.class };// 要初始化的表

	public DBHelper(Context context) {
		super(context, DBNAME, null, DBVERSION, clazz);
	}

}
