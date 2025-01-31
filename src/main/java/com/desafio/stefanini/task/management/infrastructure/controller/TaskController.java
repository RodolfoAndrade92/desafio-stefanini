package com.desafio.stefanini.task.management.infrastructure.controller;

import com.desafio.stefanini.task.management.application.dto.CreateTaskRequest;
import com.desafio.stefanini.task.management.application.dto.TaskResponse;
import com.desafio.stefanini.task.management.application.dto.UpdateTaskRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Task Management", description = "API for task management system (To-Do List)")
@RequestMapping("/api/tasks")
public interface TaskController {

    @Operation(description = "Create a task")
    @ApiResponse(responseCode = "201", description = "Task successfully created")
    @PostMapping("/create")
    ResponseEntity<TaskResponse> create(@RequestBody CreateTaskRequest createTaskRequest);

    @Operation(description = "List tasks")
    @ApiResponse(responseCode = "200", description = "Tasks successfully fetched")
    @GetMapping("/get")
    ResponseEntity<List<TaskResponse>> get();

    @Operation(description = "Update a task")
    @ApiResponse(responseCode = "200", description = "Task successfully updated")
    @PutMapping("/update")
    ResponseEntity<TaskResponse> update(@RequestBody UpdateTaskRequest updateTaskRequest);

    @Operation(description = "Delete a task")
    @ApiResponse(responseCode = "204", description = "Task successfully deleted")
    @PostMapping("/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
