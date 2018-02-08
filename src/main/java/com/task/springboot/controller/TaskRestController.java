package com.task.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.task.springboot.model.Task;
import com.task.springboot.service.TaskService;
import com.task.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/task")
public class TaskRestController {
	@Autowired
	TaskService taskService;
	
	public static final Logger logger = LoggerFactory.getLogger(TaskRestController.class);

	
	@RequestMapping(value = "/all/", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> listAllTasks() {
		List<Task> tasks = taskService.findAllTasks();
		
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add/", method = RequestMethod.POST)
	public ResponseEntity<?> saveTasks(@RequestBody Task task, UriComponentsBuilder ucBuilder) {
		taskService.saveTask(task);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/task/all/{id}").buildAndExpand(task.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTask(@PathVariable("id") long id, @RequestBody Task task) {
		logger.info("Updating Task with id {}", id);

		Task currentTask = taskService.findById(id);

		if (currentTask == null) {
			logger.error("Unable to update. Task with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Task with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentTask.setTaskName(task.getTaskName());
		currentTask.setTaskType(task.getTaskType());

		taskService.updateTask(currentTask);
		return new ResponseEntity<Task>(currentTask, HttpStatus.OK);
	}

	// ------------------- Delete a Task-----------------------------------------

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTask(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Task with id {}", id);

		Task task = taskService.findById(id);
		if (task == null) {
			logger.error("Unable to delete. Task with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Task with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		taskService.deleteTaskById(id);
		return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
	}
}
