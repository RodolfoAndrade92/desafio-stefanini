package com.desafio.stefanini.task.management.infrastructure.controller.impl;

import com.desafio.stefanini.task.management.application.dto.CreateTaskRequest;
import com.desafio.stefanini.task.management.application.dto.TaskResponse;
import com.desafio.stefanini.task.management.application.dto.UpdateTaskRequest;
import com.desafio.stefanini.task.management.application.service.TaskService;
import com.desafio.stefanini.task.management.infrastructure.controller.TaskController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class TaskControllerImpl implements TaskController {
    private final TaskService taskService;

    public TaskControllerImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<TaskResponse> create(CreateTaskRequest createTaskRequest) {
        TaskResponse taskResponse = taskService.createTask(createTaskRequest);
        return ResponseEntity.created(URI.create("tasks/create")).body(taskResponse);
    }

    @Override
    public ResponseEntity<List<TaskResponse>> get() {
        List<TaskResponse> taskResponses = taskService.getAllTasks();
        return ResponseEntity.ok(taskResponses);
    }

    @Override
    public ResponseEntity<TaskResponse> update(UpdateTaskRequest updateTaskRequest) {
        TaskResponse taskResponse = taskService.updateTask(updateTaskRequest);
        return ResponseEntity.ok(taskResponse);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
