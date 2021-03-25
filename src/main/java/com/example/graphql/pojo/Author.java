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
@Table(name = "authors", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
@SequenceGenerator(name = "AUTHOR_SEQ_GENERATOR", sequenceName = "AUTHOR_SEQ", initialValue = 1, allocationSize = 1)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_SEQ_GENERATOR")
    @GraphQLQuery(name = "id")
    private String id;
    @Column(length = 30, nullable = true)
    @GraphQLQuery(name = "authorName")
    private String authorName;
    @Column(length = 30, nullable = true)
    @GraphQLQuery(name = "price")
    private String price;
}