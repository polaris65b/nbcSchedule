package com.example.nbcschedule.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String task;
    private final String name;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public ScheduleResponseDto(Long id, String task, String name, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.task = task;
        this.name = name;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
