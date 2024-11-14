package com.example.schedulerjpa.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private final Long userId;

    private final String title;

    private final String contents;

    public ScheduleRequestDto(Long userId, String title, String contents) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
    }
}
