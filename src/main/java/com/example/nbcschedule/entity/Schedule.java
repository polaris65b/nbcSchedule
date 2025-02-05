package com.example.nbcschedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Schedule {

    @Setter private Long id;
    private String task;
    private String password;
    private String Name;
    @Setter private LocalDateTime createdDate;
    @Setter private LocalDateTime updatedDate;

    public Schedule(String task, String password, String Name) {
        this.task = task;
        this.password = password;
        this.Name = Name;
    }

    public Schedule(Long id,
                    String task,
                    String Name,
                    LocalDateTime createdDate,
                    LocalDateTime updatedDate
    ) {
        this.id = id;
        this.task = task;
        this.Name = Name;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public void update(String task, String memberName ) {
        this.task = task;
        this.Name = memberName;
    }
}