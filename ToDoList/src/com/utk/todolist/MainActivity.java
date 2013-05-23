package com.utk.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText editTodo;
	private ListView listView;
	private ToDoDBAdapter toDoDBAdapter;
	private Cursor toDoListCursor;
	private ArrayList<Integer> todoIds = new ArrayList<Integer>();
	private ArrayList<String> todoItems = new ArrayList<String>();
	private ArrayAdapter<String> aa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editTodo = (EditText) findViewById(R.id.editTodo);
		listView = (ListView) findViewById(R.id.listView);
		aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, todoItems);

		toDoDBAdapter = new ToDoDBAdapter(this);
		toDoDBAdapter.open();
		polulateToDoList();

		listView.setAdapter(aa);

		editTodo.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((keyCode == KeyEvent.KEYCODE_ENTER)
						&& (event.getAction() == KeyEvent.ACTION_DOWN)
						&& !editTodo.getText().toString().equals("")) {
					String toDo = editTodo.getText().toString();
					// todoItems.add(toDo);
					// todoItems.add(0, toDo);

					toDoDBAdapter.insertTask(toDo);

					updateArray();

					editTodo.setText("");
					aa.notifyDataSetChanged();
					return true;
				}
				return false;
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				int task_id = todoIds.get(position);
				String task = todoItems.get(position);
				Intent intent = new Intent(MainActivity.this,
						DeleteActivity.class);
				intent.putExtra(DeleteActivity.EXTRA_ID, task_id);
				intent.putExtra(DeleteActivity.EXTRA_TASK, task);
				startActivityForResult(intent, 0);
			}

		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0) {
			switch (resultCode) {
			case RESULT_OK:
				String status = data.getStringExtra("status");
				Toast.makeText(this, "OK, " + status, Toast.LENGTH_LONG).show();
				updateArray();
				break;
			case RESULT_CANCELED:
				String status_cancel = data.getStringExtra("status");
				Toast.makeText(this, "CANCELED, " + status_cancel, Toast.LENGTH_LONG).show();
			default:
				break;
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	private void polulateToDoList() {
		// get all data from the database
		toDoListCursor = toDoDBAdapter.getAllToDoItems();
		startManagingCursor(toDoListCursor);
		// update the array list
		updateArray();
	}

	private void updateArray() {
		toDoListCursor.requery();
		// clear items in the array list
		todoIds.clear();
		todoItems.clear();

		if (toDoListCursor.moveToFirst()) {
			do {
				int id = toDoListCursor.getInt(toDoListCursor
						.getColumnIndex(ToDoDBAdapter.KEY_ID));
				String task = toDoListCursor.getString(toDoListCursor
						.getColumnIndex(ToDoDBAdapter.KEY_TASK));
				todoIds.add(0, id);
				todoItems.add(0, task);
			} while (toDoListCursor.moveToNext());
		}

		aa.notifyDataSetChanged();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// close the database connection
		toDoDBAdapter.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
