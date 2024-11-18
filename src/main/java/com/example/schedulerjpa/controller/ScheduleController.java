package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.dto.ScheduleRequestDto;
import com.example.schedulerjpa.dto.ScheduleResponseDto;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.service.ScheduleService;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.schedulerjpa.entity.User.findSessionUser;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody ScheduleRequestDto requestDto, ServletRequest request) {

        User sessionUser = findSessionUser(request);

        ScheduleResponseDto savedScheduledDto = scheduleService.saveSchedule(sessionUser.getId(), requestDto.getTitle(), requestDto.getContents());

        return new ResponseEntity<>(savedScheduledDto, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(ServletRequest request) {

        User sessionUser = findSessionUser(request);

        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll(sessionUser);

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        ScheduleResponseDto scheduleFoundById = scheduleService.findScheduleByIdOrElseThrow(id);

        return new ResponseEntity<>(scheduleFoundById, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto updatedResponseDto = scheduleService.updateSchedule(id, requestDto.getTitle(), requestDto.getContents());

        return new ResponseEntity<>(updatedResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {

        scheduleService.deleteSchedule(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

}
