package com.desafio.stefanini.task.management.domain.model;

import com.desafio.stefanini.task.management.domain.enums.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createDate;
    private TaskStatus status;
}
