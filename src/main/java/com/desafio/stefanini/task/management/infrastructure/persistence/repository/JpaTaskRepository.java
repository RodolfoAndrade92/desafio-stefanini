package com.desafio.stefanini.task.management.infrastructure.persistence.repository;

import com.desafio.stefanini.task.management.infrastructure.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTaskRepository extends JpaRepository<TaskEntity, Long> {
}
