package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.dto.SignUpRequestDto;
import com.example.schedulerjpa.dto.SignUpResponseDto;
import com.example.schedulerjpa.dto.UserResponseDto;
import com.example.schedulerjpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto = userService.signUp(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById (@PathVariable Long id){

        UserResponseDto FoundUserResponseDto = userService.findUserByIdOrElseThrow(id);

        return new ResponseEntity<>(FoundUserResponseDto, HttpStatus.OK);

    }

}
