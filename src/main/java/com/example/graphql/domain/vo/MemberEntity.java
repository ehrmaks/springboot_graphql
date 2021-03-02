package com.example.graphql.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 20)
    private String userNm;

    @CreationTimestamp
    private LocalDateTime createDt;

    @CreationTimestamp
    private LocalDateTime updateDt;
}
