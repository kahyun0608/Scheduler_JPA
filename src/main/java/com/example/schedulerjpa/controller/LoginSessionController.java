package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.dto.LoginRequestDto;
import com.example.schedulerjpa.dto.LoginResponseDto;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.repository.UserRepository;
import com.example.schedulerjpa.service.LoginService;
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
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
public class LoginSessionController {

    private final LoginService loginService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<Void> login(
        @Valid @RequestBody LoginRequestDto requestDto,
        HttpServletRequest request
    ){
        LoginResponseDto loginResponseDto = loginService.login(requestDto.getEmail(), requestDto.getPassword());
        Long userId = loginResponseDto.getUserId();

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        //세션 요청 : default value -> true
        HttpSession session = request.getSession();

        //이메일, 비밀번호로 조회한 userId로 유저 정보를 조회
        User loginUser = userRepository.findByIdOrElseThrow(userId);

        //세션의 key 와 Value 설정
        session.setAttribute(Const.LOGIN_USER, loginUser);

        return new ResponseEntity<>(HttpStatus.OK);

    }


}
