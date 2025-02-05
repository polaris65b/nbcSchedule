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

    // 일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto request) {
        Schedule schedule = scheduleRepositroy.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id의 스케줄이 존재하지 않습니다.")
        );
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        schedule.update(request.getTask(), request.getName());
        Schedule updatedSchedule = scheduleRepositroy.update(schedule);

        return new ScheduleResponseDto(
                updatedSchedule.getId(),
                updatedSchedule.getTask(),
                updatedSchedule.getName(),
                updatedSchedule.getCreatedDate(),
                updatedSchedule.getUpdatedDate()
        );
    }

    // 일정 삭제
    @Transactional
    public void deleteSchedule(Long id, String password) {
        Schedule schedule = scheduleRepositroy.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id의 스케줄이 존재하지 않습니다.")
        );

        if (!schedule.getPassword().equals(password)) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        scheduleRepositroy.deleteById(id);
    }
}
