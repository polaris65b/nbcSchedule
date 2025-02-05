package com.example.nbcschedule.service;

import com.example.nbcschedule.dto.ScheduleRequestDto;
import com.example.nbcschedule.dto.ScheduleResponseDto;
import com.example.nbcschedule.entity.Schedule;
import com.example.nbcschedule.repository.ScheduleRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepositroy scheduleRepositroy;

    // 일정 생성
    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto dto){
        Schedule schedule = new Schedule(
                dto.getTask(),
                dto.getName(),
                dto.getPassword()
        );
        Schedule savedSchedule = scheduleRepositroy.save(schedule);

        return new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getTask(),
                savedSchedule.getName(),
                savedSchedule.getCreatedDate(),
                savedSchedule.getUpdatedDate()
        );
    }
}
