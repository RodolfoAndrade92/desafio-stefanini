package com.desafio.stefanini.task.management.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request to create a new task")
public class CreateTaskRequest {

    @Schema(description = "Task title", example = "Pick up children")
    private String title;

    @Schema(description = "Task description", example = "Pick up the children from school")
    private String description;
}
