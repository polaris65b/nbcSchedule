package com.example.nbcschedule.controller;

import com.example.nbcschedule.dto.ScheduleRequestDto;
import com.example.nbcschedule.dto.ScheduleResponseDto;
import com.example.nbcschedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> save(
            @RequestBody ScheduleRequestDto dto
    ) {
        return ResponseEntity.ok(scheduleService.save(dto));
    }

    // 전체 일정 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    // 선택 일정 조회
    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> selectById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    // 일정 수정
    @PutMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto request
    ) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, request));
    }

    // 일정 삭제
    @DeleteMapping("/schedules/{id}")
    public void deleteSchedule(@PathVariable Long id, @RequestParam String password) {
        scheduleService.deleteSchedule(id, password);
    }
}
