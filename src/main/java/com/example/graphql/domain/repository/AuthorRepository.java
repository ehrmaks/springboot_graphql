//package com.example.graphql.domain.repository;
//
//import com.example.graphql.pojo.Author;
//import com.example.graphql.pojo.Post;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface AuthorRepository extends JpaRepository<Author, Integer>, QuerydslPredicateExecutor<Author> {
//    Author findById(String authorId);
//
//    Author findOne(String authorName);
//
//    void delete(String authorName);
//}
