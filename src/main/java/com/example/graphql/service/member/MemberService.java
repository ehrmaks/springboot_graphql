package com.example.graphql.service.member;

import com.example.graphql.domain.repository.MemberRepository;
import com.example.graphql.domain.vo.MemberEntity;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@GraphQLApi
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @GraphQLQuery(name = "getAllMembers")
    public List<MemberEntity> getAllMembers() {
        return memberRepository.findAll();
    }

    @GraphQLQuery(name = "getMember")
    public MemberEntity getMember(Integer memberNo) {
        // 받는 파라미터명이 스키마의 변수명과 일치하여야 함.
        return memberRepository.findById(memberNo).get();
    }
}
