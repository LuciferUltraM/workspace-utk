package com.utk.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	private EditText editTodo;
	private ListView listView;
	
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
		
//		todoItems.add("Test 1");
//		todoItems.add("Test 2");
		listView.setAdapter(aa);
		
		editTodo.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((keyCode == KeyEvent.KEYCODE_ENTER)
						&& (event.getAction() == KeyEvent.ACTION_DOWN)
						&& !editTodo.getText().toString().equals("")) {
					String toDo = editTodo.getText().toString();
//					todoItems.add(toDo);
					todoItems.add(0, toDo);
					editTodo.setText("");
					aa.notifyDataSetChanged();
					return true;
				}
				return false;
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
