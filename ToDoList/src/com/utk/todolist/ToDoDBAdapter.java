package com.utk.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoDBAdapter {
	private static final String DATABASE_NAME = "ToDoList.db";
	public static final String TABLE_NAME = "ToDoItems";
	private static final int DATABASE_VERSION = 1;
	private static final String KEY_ID = "id";
	public static final String KEY_TASK = "task";
	
	private SQLiteDatabase db;
	private final Context context; // for storing reference to the main activity
	
	public ToDoDBAdapter(Context context) {
		this.context = context; //copy to our local variable
		
		
	}
	
	private static class ToDoDBOpenHelper extends SQLiteOpenHelper {

		public ToDoDBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
