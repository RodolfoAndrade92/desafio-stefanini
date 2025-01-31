package com.desafio.stefanini.task.management.domain.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Task Status")
public enum TaskStatus {
    @Schema(description = "Task created")
    CREATED,
    @Schema(description = "Task pending")
    PENDING,
    @Schema(description = "Task in progress")
    IN_PROGRESS,
    @Schema(description = "Task done")
    DONE
}
