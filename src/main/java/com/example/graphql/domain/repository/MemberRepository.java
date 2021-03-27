package com.example.graphql.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.example.graphql.domain.vo.Member;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>, QuerydslPredicateExecutor<Member> {
    Page<Member> findAll(Pageable pageable);
    List<Member> findByNameLike(String name);
}
