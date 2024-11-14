package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.SignUpResponseDto;
import com.example.schedulerjpa.dto.UserResponseDto;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String username, String email, String password) {

        User user = new User(username, email, password);
        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getCreatedAt(), savedUser.getUpdatedAt());

    }

    public UserResponseDto findUserByIdOrElseThrow(Long id) {

        User founduser = userRepository.findByIdOrElseThrow(id);

        return new UserResponseDto(founduser.getUsername(), founduser.getEmail());

    }
}
