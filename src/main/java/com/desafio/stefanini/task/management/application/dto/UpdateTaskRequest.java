package com.desafio.stefanini.task.management.application.dto;

import com.desafio.stefanini.task.management.domain.enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request to update an existing task")
public class UpdateTaskRequest {

    @Schema(description = "Task ID", example = "1")
    private Long id;

    @Schema(description = "Task title", example = "Pick up children")
    private String title;

    @Schema(description = "Task description", example = "Pick up the children from school")
    private String description;

    @Schema(description = "Task status", example = "DONE")
    private TaskStatus status;
}
