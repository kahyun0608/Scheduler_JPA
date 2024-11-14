package com.example.schedulerjpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //N:1 단방향 연관관계 설정
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String contents;


}
