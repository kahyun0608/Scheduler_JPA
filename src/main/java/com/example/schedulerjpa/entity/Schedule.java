package com.example.schedulerjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //N:1 단방향 연관관계 설정
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @Column(nullable = false, length = 20)
    private String title;

    @Setter
    @Column(nullable = false)
    private String contents;

    public Schedule() {
    }

    public Schedule(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }
}
