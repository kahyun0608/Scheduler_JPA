package com.example.schedulerjpa.repository;

import com.example.schedulerjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginRepository extends JpaRepository<User, Long> {

    List<User> findByEmailAndPassword(String email, String password);

}
