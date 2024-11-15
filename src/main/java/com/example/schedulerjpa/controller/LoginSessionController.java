package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.dto.LoginRequestDto;
import com.example.schedulerjpa.dto.LoginResponseDto;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.repository.UserRepository;
import com.example.schedulerjpa.service.LoginService;
import com.example.schedulerjpa.service.UserService;
import common.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.example.schedulerjpa.entity.User.toUser;

@Controller
@RequiredArgsConstructor
public class LoginSessionController {

    private final LoginService loginService;
    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginRequestDto requestDto,
            HttpServletRequest request
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


}
