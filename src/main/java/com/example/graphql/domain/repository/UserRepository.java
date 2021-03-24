package com.example.graphql.domain.repository;

import com.example.graphql.domain.model.JwtUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<JwtUser, Integer>, QuerydslPredicateExecutor<JwtUser> {
    Page<JwtUser> findAll(Pageable pageable);
//    List<Member> findByNameLike(String name);
    Optional<JwtUser> findByMemberNo(String name);
}
