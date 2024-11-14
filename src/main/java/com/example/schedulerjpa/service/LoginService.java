package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.LoginResponseDto;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.repository.LoginRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository loginRepository;

    public LoginResponseDto login(@NotNull String email, @NotNull String password) {

        List<User> userList = loginRepository.findByEmailAndPassword(email, password);
        Long userId = userList.stream().findFirst().orElse(null).getId();

        return new LoginResponseDto(userId);

    }
}
