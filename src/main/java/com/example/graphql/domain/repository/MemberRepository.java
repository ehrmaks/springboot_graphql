package com.example.graphql.domain.repository;

import com.example.graphql.domain.vo.MemberVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberVo, Integer>, QuerydslPredicateExecutor<MemberVo> {
    Page<MemberVo> findAll(Pageable pageable);
    MemberVo findByEmail(String email);
    @EntityGraph(attributePaths = "authorities")
    Optional<MemberVo> findOneWithAuthoritiesByEmail(String email);
}
