package com.example.graphql.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(length = 30, nullable = false)
    private String userName;

    @CreationTimestamp
    private LocalDateTime createdDt;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
