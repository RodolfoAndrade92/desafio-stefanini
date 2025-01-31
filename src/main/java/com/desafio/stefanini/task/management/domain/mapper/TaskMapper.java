package com.desafio.stefanini.task.management.domain.mapper;

import com.desafio.stefanini.task.management.application.dto.CreateTaskRequest;
import com.desafio.stefanini.task.management.application.dto.TaskResponse;
import com.desafio.stefanini.task.management.application.dto.UpdateTaskRequest;
import com.desafio.stefanini.task.management.domain.enums.TaskStatus;
import com.desafio.stefanini.task.management.domain.model.Task;
import com.desafio.stefanini.task.management.infrastructure.persistence.entity.TaskEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskMapper {

    public Task convertToTask(CreateTaskRequest createTaskRequest) {
        return Task.builder()
                .title(createTaskRequest.getTitle())
                .description(createTaskRequest.getDescription())
                .createDate(LocalDateTime.now())
                .status(TaskStatus.CREATED)
                .build();
    }

    public Task convertToTask(UpdateTaskRequest updateTaskRequest, Task task) {
        Optional.ofNullable(updateTaskRequest.getTitle()).ifPresent(task::setTitle);
        Optional.ofNullable(updateTaskRequest.getDescription()).ifPresent(task::setDescription);
        Optional.ofNullable(updateTaskRequest.getStatus()).ifPresent(task::setStatus);
        return task;
    }

    public Task convertToTask(TaskEntity taskEntity) {
        return Task.builder()
                .title(taskEntity.getTitle())
                .description(taskEntity.getDescription())
                .createDate(taskEntity.getCreateDate())
                .status(taskEntity.getStatus())
                .build();
    }

    public TaskEntity convertToTaskEntity(Task task) {
        return TaskEntity.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .createDate(task.getCreateDate())
                .status(task.getStatus())
                .build();
    }

    public List<Task> convertToTaskList(List<TaskEntity> taskEntityList) {
        List<Task> taskList = new ArrayList<>();
        taskEntityList.forEach(taskEntity -> taskList.add(this.convertToTask(taskEntity)));
        return taskList;
    }

    public TaskResponse convertToTaskResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .createDate(task.getCreateDate())
                .status(task.getStatus())
                .build();
    }

    public List<TaskResponse> convertToTaskResponseList(List<Task> taskList) {
        List<TaskResponse> taskResponseList = new ArrayList<>();
        taskList.forEach(task -> taskResponseList.add(this.convertToTaskResponse(task)));
        return taskResponseList;
    }
}
