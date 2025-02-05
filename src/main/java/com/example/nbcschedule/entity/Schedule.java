package com.example.nbcschedule.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Schedule {

    private Long id;
    private String task;
    private String name;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Schedule(String task,
                    String name,
                    String password,
                    LocalDateTime createdDate,
                    LocalDateTime updatedDate)
    {
        this.task = task;
        this.name = name;
        this.password = password;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
