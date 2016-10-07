package com.example.eventmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{
	public DataBaseHelper(Context context, String name,CursorFactory factory, int version) 
    {
	           super(context, name, factory, version);
	}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{
				db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);
				db.execSQL(LoginDataBaseAdapter.NEW_DATABASE_CREATE);
				db.execSQL(LoginDataBaseAdapter.ADMIN_TABLE);
				db.execSQL(LoginDataBaseAdapter.JOINT_TABLE);
				db.execSQL(LoginDataBaseAdapter.IMAGE_TABLE_CREATE);
	 
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
				// Log the version upgrade.
				Log.w("TaskDBAdapter", "Upgrading from version " +oldVersion + " to " +newVersion + ", which will destroy all old data");
				db.execSQL("DROP TABLE IF EXISTS " + "REGISTRATION");
				db.execSQL("DROP TABLE IF EXISTS " + "TIMELINE");
				db.execSQL("DROP TABLE IF EXISTS " + "ADMIN_TABLE");
				db.execSQL("DROP TABLE IF EXISTS " + "JOINT_TABLE");
				db.execSQL("DROP TABLE IF EXISTS " + "IMAGE_TABLE_CREATE");
				// Create a new one.
				
				onCreate(db);
		}
		

}
