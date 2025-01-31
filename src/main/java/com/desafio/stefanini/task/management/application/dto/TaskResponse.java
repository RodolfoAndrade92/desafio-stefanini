package com.desafio.stefanini.task.management.application.dto;

import com.desafio.stefanini.task.management.domain.enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response containing details of a task")
public class TaskResponse {

    @Schema(description = "Task ID", example = "1")
    private Long id;

    @Schema(description = "Task title", example = "Pick up children")
    private String title;

    @Schema(description = "Task description", example = "Pick up the children from school")
    private String description;

    @Schema(description = "Task creation date", example = "2023-10-01T10:00:00")
    private LocalDateTime createDate;

    @Schema(description = "Task status", example = "IN PROGRESS")
    private TaskStatus status;
}
