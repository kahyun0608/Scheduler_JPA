package com.example.schedulerjpa.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    @NotNull
    private final String email;

    @NotNull
    private  final String password;

}
