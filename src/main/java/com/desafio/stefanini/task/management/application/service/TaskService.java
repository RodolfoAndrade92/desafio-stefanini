package com.desafio.stefanini.task.management.application.service;

import com.desafio.stefanini.task.management.application.dto.CreateTaskRequest;
import com.desafio.stefanini.task.management.application.dto.TaskResponse;
import com.desafio.stefanini.task.management.application.dto.UpdateTaskRequest;
import com.desafio.stefanini.task.management.domain.mapper.TaskMapper;
import com.desafio.stefanini.task.management.domain.model.Task;
import com.desafio.stefanini.task.management.domain.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskMapper mapper;
    private final TaskRepository taskRepository;

    public TaskService(TaskMapper mapper, TaskRepository taskRepository) {
        this.mapper = mapper;
        this.taskRepository = taskRepository;
    }

    public TaskResponse createTask(CreateTaskRequest createTaskRequest) {
        Task task = mapper.convertToTask(createTaskRequest);
        Task createdTask = taskRepository.save(task);
        return mapper.convertToTaskResponse(createdTask);
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> allTasks = taskRepository.findAll();
        return mapper.convertToTaskResponseList(allTasks);
    }

    public TaskResponse updateTask(UpdateTaskRequest updateTaskRequest) {
        Task task = taskRepository.findById(updateTaskRequest.getId());
        Task taskToUpdate = mapper.convertToTask(updateTaskRequest, task);
        Task updatedTask = taskRepository.save(taskToUpdate);
        return mapper.convertToTaskResponse(updatedTask);
    }

    public void delete(Long id) {
        taskRepository.delete(id);
    }
}
