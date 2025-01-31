package com.desafio.stefanini.task.management.domain.mapper;

import com.desafio.stefanini.task.management.application.dto.CreateTaskRequest;
import com.desafio.stefanini.task.management.application.dto.TaskResponse;
import com.desafio.stefanini.task.management.application.dto.UpdateTaskRequest;
import com.desafio.stefanini.task.management.domain.enums.TaskStatus;
import com.desafio.stefanini.task.management.domain.model.Task;
import com.desafio.stefanini.task.management.infrastructure.persistence.entity.TaskEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertToTaskFromCreateTaskRequest() {
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("Task Title");
        createTaskRequest.setDescription("Task Description");

        Task task = taskMapper.convertToTask(createTaskRequest);

        assertAll(
                () -> assertNotNull(task),
                () -> assertEquals("Task Title", task.getTitle()),
                () -> assertEquals("Task Description", task.getDescription()),
                () -> assertEquals(TaskStatus.CREATED, task.getStatus()),
                () -> assertNotNull(task.getCreateDate())
        );
    }

    @Test
    void testConvertToTaskFromUpdateTaskRequest() {
        UpdateTaskRequest updateTaskRequest = new UpdateTaskRequest();
        updateTaskRequest.setTitle("Updated Title");
        updateTaskRequest.setDescription("Updated Description");
        updateTaskRequest.setStatus(TaskStatus.IN_PROGRESS);

        Task existingTask = Task.builder()
                .title("Old Title")
                .description("Old Description")
                .status(TaskStatus.CREATED)
                .build();

        Task updatedTask = taskMapper.convertToTask(updateTaskRequest, existingTask);

        assertAll(
                () -> assertNotNull(updatedTask),
                () -> assertEquals("Updated Title", updatedTask.getTitle()),
                () -> assertEquals("Updated Description", updatedTask.getDescription()),
                () -> assertEquals(TaskStatus.IN_PROGRESS, updatedTask.getStatus())
        );
    }

    @Test
    void testConvertToTaskFromTaskEntity() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle("Task Title");
        taskEntity.setDescription("Task Description");
        taskEntity.setCreateDate(LocalDateTime.now());
        taskEntity.setStatus(TaskStatus.CREATED);

        Task task = taskMapper.convertToTask(taskEntity);

        assertAll(
                () -> assertNotNull(task),
                () -> assertEquals("Task Title", task.getTitle()),
                () -> assertEquals("Task Description", task.getDescription()),
                () -> assertEquals(taskEntity.getCreateDate(), task.getCreateDate()),
                () -> assertEquals(TaskStatus.CREATED, task.getStatus())
        );
    }

    @Test
    void testConvertToTaskEntityFromTask() {
        Task task = Task.builder()
                .title("Task Title")
                .description("Task Description")
                .createDate(LocalDateTime.now())
                .status(TaskStatus.CREATED)
                .build();

        TaskEntity taskEntity = taskMapper.convertToTaskEntity(task);

        assertAll(
                () -> assertNotNull(taskEntity),
                () -> assertEquals("Task Title", taskEntity.getTitle()),
                () -> assertEquals("Task Description", taskEntity.getDescription()),
                () -> assertEquals(task.getCreateDate(), taskEntity.getCreateDate()),
                () -> assertEquals(TaskStatus.CREATED, taskEntity.getStatus())
        );
    }

    @Test
    void testConvertToTaskListFromTaskEntityList() {
        TaskEntity taskEntity1 = new TaskEntity();
        taskEntity1.setTitle("Task 1");
        taskEntity1.setDescription("Description 1");
        taskEntity1.setCreateDate(LocalDateTime.now());
        taskEntity1.setStatus(TaskStatus.CREATED);

        TaskEntity taskEntity2 = new TaskEntity();
        taskEntity2.setTitle("Task 2");
        taskEntity2.setDescription("Description 2");
        taskEntity2.setCreateDate(LocalDateTime.now());
        taskEntity2.setStatus(TaskStatus.IN_PROGRESS);

        List<TaskEntity> taskEntityList = Arrays.asList(taskEntity1, taskEntity2);

        List<Task> taskList = taskMapper.convertToTaskList(taskEntityList);

        assertAll(
                () -> assertNotNull(taskList),
                () -> assertEquals(2, taskList.size()),
                () -> assertEquals("Task 1", taskList.get(0).getTitle()),
                () -> assertEquals("Task 2", taskList.get(1).getTitle())
        );
    }

    @Test
    void testConvertToTaskResponseFromTask() {
        Task task = Task.builder()
                .id(1L)
                .title("Task Title")
                .description("Task Description")
                .createDate(LocalDateTime.now())
                .status(TaskStatus.CREATED)
                .build();

        TaskResponse taskResponse = taskMapper.convertToTaskResponse(task);

        assertAll(
                () -> assertNotNull(taskResponse),
                () -> assertEquals(1L, taskResponse.getId()),
                () -> assertEquals("Task Title", taskResponse.getTitle()),
                () -> assertEquals("Task Description", taskResponse.getDescription()),
                () -> assertEquals(task.getCreateDate(), taskResponse.getCreateDate()),
                () -> assertEquals(TaskStatus.CREATED, taskResponse.getStatus())
        );
    }

    @Test
    void testConvertToTaskResponseListFromTaskList() {
        Task task1 = Task.builder()
                .id(1L)
                .title("Task 1")
                .description("Description 1")
                .createDate(LocalDateTime.now())
                .status(TaskStatus.CREATED)
                .build();

        Task task2 = Task.builder()
                .id(2L)
                .title("Task 2")
                .description("Description 2")
                .createDate(LocalDateTime.now())
                .status(TaskStatus.IN_PROGRESS)
                .build();

        List<Task> taskList = Arrays.asList(task1, task2);

        List<TaskResponse> taskResponseList = taskMapper.convertToTaskResponseList(taskList);

        assertAll(
                () -> assertNotNull(taskResponseList),
                () -> assertEquals(2, taskResponseList.size()),
                () -> assertEquals("Task 1", taskResponseList.get(0).getTitle()),
                () -> assertEquals("Task 2", taskResponseList.get(1).getTitle())
        );
    }
}