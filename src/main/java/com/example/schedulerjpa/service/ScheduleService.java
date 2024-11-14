package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.ScheduleResponseDto;
import com.example.schedulerjpa.entity.Schedule;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.repository.ScheduleRepository;
import com.example.schedulerjpa.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    public List<ScheduleResponseDto> findAll(User sessionUser) {
        return scheduleRepository.findByUserId(sessionUser.getId()).stream().map(ScheduleResponseDto::toDto).toList();
    }

    public ScheduleResponseDto findScheduleByIdOrElseThrow(Long id) {
        return toDto(scheduleRepository.findScheduleByIdOrElseThrow(id));
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents) {

        Schedule scheduleFoundById = scheduleRepository.findScheduleByIdOrElseThrow(id);

        if (title == null && contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title or content to change is required.");
        }

        if (title != null) {
            scheduleFoundById.setTitle(title);
        }
        if (contents != null) {
            scheduleFoundById.setContents(contents);
        }

        return toDto(scheduleFoundById);
    }

    public void deleteSchedule(Long id) {

        Schedule scheduleFoundById = scheduleRepository.findScheduleByIdOrElseThrow(id);

        scheduleRepository.delete(scheduleFoundById);

    }
}
