package com.task.springboot.service;

import java.util.List;

import com.task.springboot.model.Task;

public interface TaskService {
	
	Task findById(Long id);

	Task findByTaskName(String name);

	void saveTask(Task task);

	void updateTask(Task task);

	void deleteTaskById(Long id);

	void deleteAllTasks();

	List<Task> findAllTasks();

}