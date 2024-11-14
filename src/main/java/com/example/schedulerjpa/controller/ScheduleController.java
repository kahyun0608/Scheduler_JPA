package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.dto.ScheduleRequestDto;
import com.example.schedulerjpa.dto.ScheduleResponseDto;
import com.example.schedulerjpa.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/post")
    public ResponseEntity<ScheduleResponseDto> saveSchedule (@RequestBody ScheduleRequestDto requestDto){

        ScheduleResponseDto savedScheduledDto = scheduleService.saveSchedule(requestDto.getUserId(), requestDto.getTitle(), requestDto.getContents());

        return new ResponseEntity<>(savedScheduledDto, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll () {

        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById (@PathVariable Long id) {
        ScheduleResponseDto scheduleFoundById = scheduleService.findScheduleByIdOrElseThrow(id);

        return new ResponseEntity<>(scheduleFoundById, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule (@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto){
        ScheduleResponseDto updatedResponseDto = scheduleService.updateSchedule(id, requestDto.getTitle(), requestDto.getContents());

        return new ResponseEntity<>(updatedResponseDto, HttpStatus.OK);
    }



}
