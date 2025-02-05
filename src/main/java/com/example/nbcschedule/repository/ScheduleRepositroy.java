package com.example.nbcschedule.repository;

import com.example.nbcschedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepositroy {

    Schedule save(Schedule schedule);
    List<Schedule> findAll();
    Optional<Schedule> findById(Long id);

    void deleteById(Long id);
}
