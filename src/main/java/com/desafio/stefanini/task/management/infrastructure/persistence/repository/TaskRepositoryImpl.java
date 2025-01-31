package com.desafio.stefanini.task.management.infrastructure.persistence.repository;

import com.desafio.stefanini.task.management.domain.mapper.TaskMapper;
import com.desafio.stefanini.task.management.domain.model.Task;
import com.desafio.stefanini.task.management.domain.repository.TaskRepository;
import com.desafio.stefanini.task.management.infrastructure.persistence.entity.TaskEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskRepositoryImpl implements TaskRepository {

    final TaskMapper mapper;
    final JpaTaskRepository jpaTaskRepository;

    public TaskRepositoryImpl(TaskMapper mapper, JpaTaskRepository jpaTaskRepository) {
        this.mapper = mapper;
        this.jpaTaskRepository = jpaTaskRepository;
    }

    @Override
    public Task save(Task task) {
        TaskEntity saved = jpaTaskRepository.save(mapper.convertToTaskEntity(task));
        return mapper.convertToTask(saved);
    }

    @Override
    public Task findById(Long id) {
        TaskEntity taskEntity = jpaTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return mapper.convertToTask(taskEntity);
    }

    @Override
    public List<Task> findAll() {
        List<TaskEntity> taskEntityList = jpaTaskRepository.findAll();
        return mapper.convertToTaskList(taskEntityList);
    }

    @Override
    public void delete(Long id) {
        jpaTaskRepository.deleteById(id);
    }
}
