package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.LoginResponseDto;
import com.example.schedulerjpa.dto.SignUpResponseDto;
import com.example.schedulerjpa.dto.UserResponseDto;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Getter
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String username, String email, String password) {

        Optional<User> findUser = userRepository.findByEmail(email);

        if (findUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하는 이메일입니다.");
        }

        User user = new User(username, email, password);

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getCreatedAt(), savedUser.getUpdatedAt());

    }

    public UserResponseDto findUserByIdOrElseThrow(Long id) {

        User foundUser = userRepository.findByIdOrElseThrow(id);

        return new UserResponseDto(foundUser.getUsername(), foundUser.getEmail());

    }

    public void deleteUserById(Long id, String password) {

        User foundUser = userRepository.findByIdOrElseThrow(id);

        if (foundUser.getPassword().equals(password)) {
            userRepository.delete(foundUser);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong Password.");
        }

    }

    public LoginResponseDto login(String email, String password) {

        List<User> userList = userRepository.findByEmailAndPassword(email, password);
        Long userId = userList.stream().findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED)).getId();

        return new LoginResponseDto(userId);

    }

}
