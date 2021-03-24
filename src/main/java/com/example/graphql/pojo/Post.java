package com.example.graphql.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    private  String id;
    @Column(length = 30, nullable = false)
    private String authorId;
    private String orderProceed;
    private  String price;
    private  String quantity;
}