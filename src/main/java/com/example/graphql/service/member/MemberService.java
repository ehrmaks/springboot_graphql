package com.example.graphql.service.member;

import com.example.graphql.domain.repository.MemberRepository;
import com.example.graphql.domain.vo.MemberEntity;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@GraphQLApi
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    /*
        query {
            getAllMembers {
                memberNo
                userId
                ...
            }
        }
    * */
    @GraphQLQuery(name = "getAllMembers")
    public List<MemberEntity> getAllMembers() {
        return memberRepository.findAll();
    }

    /*
        query {
            getMember(memberNo: 25) {
                userId
                ...
            }
        }
    * */
    @GraphQLQuery(name = "getMember")
    public MemberEntity getMember(Integer memberNo) {
        // 받는 파라미터명이 스키마의 변수명과 일치하여야 함.
        return memberRepository.findById(memberNo).get();
    }

    /*
        mutation {
            insertMember(memberEntity : {
                address1: "seoul"
                address2: "sillim"
                email: "qwefk12345@naver.com"
                name: "jskim"
                passwd: "12345"
                postNo: "02334"
                userId: "ehrmaks1234"
                profileImg: ""
                phoneNo: "010-3524-0022"
            })
        }
    * */
    @Transactional
    @GraphQLMutation(name = "insertMember")
    public int insertMember(MemberEntity memberEntity) {
        memberEntity.setSecYn("N");
        memberEntity.setUseYn("Y");
        Integer memberNo = memberRepository.save(memberEntity).getMemberNo();
        if (memberNo != null) return 1;
        else return 0;
    }

    /*
        mutation {
            updateMember(memberEntity : {
              memberNo: 26
                address1: "seoul"
                address2: "sillim"
                email: "qwefk1234@naver.com"
                name: "jskim"
                passwd: "1234"
                postNo: "02334"
                userId: "ehrmaks123"
                profileImg: ""
                phoneNo: "010-3524-0022"
            })
        }
    * */
    @Transactional
    @GraphQLMutation(name = "updateMember")
    public int updateMember(MemberEntity memberEntity) {
        Integer memberNo = memberEntity.getMemberNo();
        if (memberEntity.getMemberNo() != null) {
            memberEntity.setSecYn("N");
            memberEntity.setUseYn("Y");
            memberRepository.save(memberEntity);
            return 1;
        }
        return 0;
    }

    /*
        mutation{
          deleteMember(memberNo: 28)
        }
    * */
    @Transactional
    @GraphQLMutation(name = "deleteMember")
    public int deleteMember(Integer memberNo) {
        if (memberNo != null) {
            memberRepository.deleteById(memberNo);
            return 1;
        }
        return 0;
    }
}
