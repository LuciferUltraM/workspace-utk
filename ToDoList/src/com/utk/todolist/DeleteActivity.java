package com.utk.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DeleteActivity extends Activity {
	
	public static final String EXTRA_ID = "DeleteActivity.EXTRA_ID";
	public static final String EXTRA_TASK = "DeleteActivity.EXTRA_TASK";
	
	private Intent intent;
	private int id;
	private String task;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);
		
		TextView textViewTask = (TextView) findViewById(R.id.textViewTask);
		Button buttonDelete = (Button) findViewById(R.id.buttonDelete);
		Button buttonCancel = (Button) findViewById(R.id.cancelDelete);
		
		intent = getIntent();
		id = intent.getIntExtra(EXTRA_ID, 0);
		task = intent.getStringExtra(EXTRA_TASK);
		
		textViewTask.setText(task);
		
		buttonDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ToDoDBAdapter toDoDBAdapter = new ToDoDBAdapter(DeleteActivity.this);
				toDoDBAdapter.open();
				toDoDBAdapter.deteleByID(id);
				intent.putExtra("status", task + " deleted");
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		buttonCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent.putExtra("status", task + " is not deleted");
				setResult(RESULT_CANCELED, intent);
				finish();
			}
		});
		
	}

}
