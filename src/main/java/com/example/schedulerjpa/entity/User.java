package com.example.schedulerjpa.entity;

import com.example.schedulerjpa.dto.LoginResponseDto;
import common.Const;
import jakarta.persistence.*;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String password;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(Long id) {
        this.id = id;
    }

    //로그인한 유저 정보를 찾음
    public static User findSessionUser(ServletRequest request){
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        return (User) session.getAttribute(Const.LOGIN_USER);
    }

    public static User toUser (LoginResponseDto dto){
        return new User(dto.getUserId());
    }
}
