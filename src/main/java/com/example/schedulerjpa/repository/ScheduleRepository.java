package com.example.schedulerjpa.repository;


import com.example.schedulerjpa.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    default Schedule findScheduleByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No valid id : " + id));
    }

    List<Schedule> findByUserId(Long id);

}
