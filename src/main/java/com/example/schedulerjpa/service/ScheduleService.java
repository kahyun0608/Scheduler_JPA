package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.ScheduleResponseDto;
import com.example.schedulerjpa.entity.Schedule;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.repository.ScheduleRepository;
import com.example.schedulerjpa.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.schedulerjpa.dto.ScheduleResponseDto.toDto;

@Getter
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto saveSchedule(Long userId, String title, String contents) {

        User foundByUserId = userRepository.findByIdOrElseThrow(userId);

        Schedule schedule = new Schedule(foundByUserId, title, contents);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return toDto(savedSchedule);
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();
    }

}
