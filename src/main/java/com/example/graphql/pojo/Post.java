package com.example.graphql.pojo;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "posts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
@SequenceGenerator(name = "POST_SEQ_GENERATOR", sequenceName = "POST_SEQ", initialValue = 1, allocationSize = 1)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ_GENERATOR")
    @GraphQLQuery(name = "id")
    private  String id;
    @Column(length = 30, nullable = false)
    @GraphQLQuery(name = "authorId")
    private String authorId;
    @Column(length = 30, nullable = true)
    @GraphQLQuery(name = "orderProceed")
    private String orderProceed;
    @Column(length = 30, nullable = true)
    @GraphQLQuery(name = "price")
    private String price;
    @Column(length = 30, nullable = true)
    @GraphQLQuery(name = "quantity")
    private String quantity;

    public Post(Object o, String quantity) {
    }
}