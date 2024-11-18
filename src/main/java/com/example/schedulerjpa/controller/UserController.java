package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.dto.*;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.service.LoginService;
import com.example.schedulerjpa.service.UserService;
import common.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.schedulerjpa.entity.User.toUser;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto = userService.signUp(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request
    ) {
        //로그인을 위한 이메일과 비밀번호를 받아 DB와 비교
        LoginResponseDto loginResponseDto = loginService.login(requestDto.getEmail(), requestDto.getPassword());

        //세션 요청 : default value -> true
        HttpSession session = request.getSession();

        //이메일, 비밀번호로 조회한 userId로 유저 정보를 조회
        User loginUser = toUser(loginResponseDto);

        //세션의 key 와 Value(유저정보저장) 설정
        session.setAttribute(Const.LOGIN_USER, loginUser);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {

        UserResponseDto FoundUserResponseDto = userService.findUserByIdOrElseThrow(id);

        return new ResponseEntity<>(FoundUserResponseDto, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id, @RequestBody SignUpRequestDto requestDto) {
        userService.deleteUserById(id, requestDto.getPassword());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
