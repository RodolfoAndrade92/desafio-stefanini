package com.desafio.stefanini.task.management.application.service;

import com.desafio.stefanini.task.management.application.dto.CreateTaskRequest;
import com.desafio.stefanini.task.management.application.dto.TaskResponse;
import com.desafio.stefanini.task.management.application.dto.UpdateTaskRequest;
import com.desafio.stefanini.task.management.domain.mapper.TaskMapper;
import com.desafio.stefanini.task.management.domain.model.Task;
import com.desafio.stefanini.task.management.domain.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskServiceTest {

    @Mock
    private TaskMapper mapper;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        CreateTaskRequest request = new CreateTaskRequest();
        Task task = new Task();
        Task createdTask = new Task();
        TaskResponse response = new TaskResponse();

        when(mapper.convertToTask(request)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(createdTask);
        when(mapper.convertToTaskResponse(createdTask)).thenReturn(response);

        TaskResponse result = taskService.createTask(request);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(response, result),
                () -> verify(mapper).convertToTask(request),
                () -> verify(taskRepository).save(task),
                () -> verify(mapper).convertToTaskResponse(createdTask)
        );
    }

    @Test
    void testGetAllTasks() {
        Task task1 = new Task();
        Task task2 = new Task();
        List<Task> tasks = Arrays.asList(task1, task2);
        TaskResponse response1 = new TaskResponse();
        TaskResponse response2 = new TaskResponse();
        List<TaskResponse> responses = Arrays.asList(response1, response2);

        when(taskRepository.findAll()).thenReturn(tasks);
        when(mapper.convertToTaskResponseList(tasks)).thenReturn(responses);

        List<TaskResponse> result = taskService.getAllTasks();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(2, result.size()),
                () -> assertEquals(responses, result),
                () -> verify(taskRepository).findAll(),
                () -> verify(mapper).convertToTaskResponseList(tasks)
        );
    }

    @Test
    void testUpdateTask() {
        UpdateTaskRequest request = new UpdateTaskRequest();
        request.setId(1L);

        Task existingTask = new Task();
        Task taskToUpdate = new Task();
        Task updatedTask = new Task();
        TaskResponse response = new TaskResponse();

        when(taskRepository.findById(request.getId())).thenReturn(existingTask);
        when(mapper.convertToTask(request, existingTask)).thenReturn(taskToUpdate);
        when(taskRepository.save(taskToUpdate)).thenReturn(updatedTask);
        when(mapper.convertToTaskResponse(updatedTask)).thenReturn(response);

        TaskResponse result = taskService.updateTask(request);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(response, result),
                () -> verify(taskRepository).findById(request.getId()),
                () -> verify(mapper).convertToTask(request, existingTask),
                () -> verify(taskRepository).save(taskToUpdate),
                () -> verify(mapper).convertToTaskResponse(updatedTask)
        );
    }

    @Test
    void testDeleteTask() {
        Long taskId = 1L;

        taskService.delete(taskId);

        verify(taskRepository).delete(taskId);
    }
}