package com.example.nbcschedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Schedule {

    @Setter private Long id;
    private String task;
    private String name;
    private String password;
    @Setter private LocalDateTime createdDate;
    @Setter private LocalDateTime updatedDate;

    public Schedule(
            Long id,
            String task,
            String name,
            LocalDateTime createdDate,
            LocalDateTime updatedDate)
    {
        this.id = id;
        this.task = task;
        this.name = name;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    // Save시 필요한 생성자
    public Schedule(
            String task,
            String name,
            String password
    ) {
        this.task = task;
        this.name = name;
        this.password = password;
    }
}
