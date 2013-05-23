package com.utk.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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

	private ToDoDBOpenHelper dbHelper;

	public ToDoDBAdapter(Context context) {
		this.context = context; // copy to our local variable

		dbHelper = new ToDoDBOpenHelper(context, DATABASE_NAME, null,
				DATABASE_VERSION);
	}
	
	public void open() {
		try {
			db = dbHelper.getWritableDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
			db = dbHelper.getReadableDatabase();
		}
	}
	
	public void close() {
		// close connection to the database
		db.close();
	}
	
	public long insertTask(String task) {
		// create a new row to insert
		ContentValues newTaskValues = new ContentValues();
		// add values for each row
		newTaskValues.put(KEY_TASK, task);
		// insert into database
		return db.insert(TABLE_NAME, null, newTaskValues);
	}
	
	public Cursor getAllToDoItems() {
		// selct all todoitems from the databse
		return db.query(TABLE_NAME, new String[] { KEY_ID, KEY_TASK }, null,
				null, null, null, null);
	}
	

	private static class ToDoDBOpenHelper extends SQLiteOpenHelper {

		public ToDoDBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// Create the ToDoItems Table

			// CREATE TABLE ToDoItems (id INTEGER PRIMARY KEY AUTOINCREMENT,
			// task TEXT NOT NULL);
			String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TASK
					+ " TEXT NOT NULL);";
			db.execSQL(CREATE_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}

	}

}
