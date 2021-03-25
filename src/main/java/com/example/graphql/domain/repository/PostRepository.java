package com.example.graphql.domain.repository;

import com.example.graphql.domain.model.JwtUser;
import com.example.graphql.pojo.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, QuerydslPredicateExecutor<Post> {
    List<Post> findByAuthorId(String authorId);

    Post findOne(String id);
}
