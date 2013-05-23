package com.utk.todolist;

public class ToDo {
	private String date;
	private String task;
	public ToDo(String date, String task) {
		this.date = date;
		this.task = task;
	}
	
	public String getDate() {
		return this.date;
	}

	public String getTask() {
		return task;
	}
	
	
	
}
