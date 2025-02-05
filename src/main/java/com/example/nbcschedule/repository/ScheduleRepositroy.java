package com.example.nbcschedule.repository;

import com.example.nbcschedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepositroy {
    // Lv 1 Create, Read(All & select)
    Schedule save(Schedule schedule);
    List<Schedule> findAll();
    Optional<Schedule> findById(Long id);

    // Lv 2 Delete & Update
    void deleteById(Long id);
}
