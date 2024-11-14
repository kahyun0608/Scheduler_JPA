package com.example.schedulerjpa.repository;

import com.example.schedulerjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
