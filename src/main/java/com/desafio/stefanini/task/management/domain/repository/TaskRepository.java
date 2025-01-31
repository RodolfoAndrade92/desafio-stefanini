package com.desafio.stefanini.task.management.domain.repository;

import com.desafio.stefanini.task.management.domain.model.Task;

import java.util.List;

public interface TaskRepository {
    Task save(Task task);

    Task findById(Long id);

    List<Task> findAll();

    void delete(Long id);

}
