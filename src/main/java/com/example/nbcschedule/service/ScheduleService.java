package com.example.nbcschedule.service;

import com.example.nbcschedule.dto.ScheduleRequestDto;
import com.example.nbcschedule.dto.ScheduleResponseDto;
import com.example.nbcschedule.entity.Schedule;
import com.example.nbcschedule.repository.ScheduleRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // 전체 일정 조회
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll(){
        List<Schedule> schedules = scheduleRepositroy.findAll();

        List<ScheduleResponseDto> dtos = new ArrayList<>();
        for(Schedule schedule : schedules){
            ScheduleResponseDto dto = new ScheduleResponseDto(
                    schedule.getId(),
                    schedule.getTask(),
                    schedule.getName(),
                    schedule.getCreatedDate(),
                    schedule.getUpdatedDate()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 선택 조회
    public ScheduleResponseDto findById(Long id){
        Schedule schedule = scheduleRepositroy.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Schedule이 없습니다.")
        );

        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTask(),
                schedule.getName(),
                schedule.getCreatedDate(),
                schedule.getUpdatedDate()
        );
    }
}
