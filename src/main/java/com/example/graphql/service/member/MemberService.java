package com.example.graphql.service.member;

import com.example.graphql.model.entity.Member;
import com.example.graphql.model.repository.MemberRepository;
import com.example.graphql.model.entity.QMember;
import com.example.graphql.model.response.SingleResult;
import com.example.graphql.service.response.ResponseService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@GraphQLApi
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ResponseService responseService;

    @PersistenceContext
    private EntityManager em; // 영속성 객체를 자동으로 삽입해줌

    /*
        query {
            getAllMembers {
                memberId
                userId
                ...
            }
        }
    * */
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GraphQLQuery(name = "getAllMembers")
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    /*
        query {
          getPagingMember(page: 1, size: 5) {
            content{
              memberId
              userId
              profileImg
              address1
              address2
            }
            size
            totalPages
          }
        }
    * */
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GraphQLQuery(name = "getPagingMember")
    public Page<Member> getPagingMember(
    		@GraphQLArgument(name = "page") int page, 
    		@GraphQLArgument(name = "size") int size) {
        Pageable pageable = PageRequest.of(page, size, Direction.DESC, "memberId");
        return memberRepository.findAll(pageable);
    }

    /*
        query{
          getMemberList(page: 0, size: 5, name:"사나", email:"kjsfk") {
            success
            code
            msg
            data {
              content {
                member_id
                userId
                userName
              }
            }
          }
        }
    * */
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GraphQLQuery(name = "getMemberList")
    public SingleResult<Page<Member>> getMemberList(
    		@GraphQLArgument(name = "page") int page, 
    		@GraphQLArgument(name = "size") int size, 
    		@GraphQLArgument(name = "userName") String userName,
    		@GraphQLArgument(name = "email") String email) {
//        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
    	// 멤버 Entity 경로를 가져옴
//        QMember m = QMember.member;
//        Predicate builder = predicate(name, email);
//
//        Map<String, Object> map = new HashMap<>();
//        int totalElements = memberRepository.findAll().size();
//        int totalPage = (int) Math.ceil(totalElements / size);
//
//
//        List<Member> list = queryFactory
//                                .selectFrom(m)
//                                .where(builder)
//                                .limit(size)
//                                .offset((page - 1) * size)
//                                .orderBy(m.memberId.desc())
//                                .fetch();
//
//        map.put("data", list);
//        map.put("totalElements", totalElements);
//        map.put("totalPage", totalPage);
//
//        return map;

        // Page_Member
        Pageable pageable = PageRequest.of(page, size, Direction.DESC, "memberId");

        return responseService.getSingleResult(memberRepository.findAll(predicate(userName, email), pageable));
    }

    // Predicate 필터 조건 설정
    public Predicate predicate(String userName, String email) {
        // 멤버 Entity 경로를 가져옴
        QMember m = QMember.member;

        // where 조건절의 동적 쿼리를 위한 셋팅
        BooleanBuilder builder = new BooleanBuilder();

        if (userName != null) {
            builder.and(m.userName.like("%" + userName + "%"));
        }

        if (email != null) {
            builder.and(m.email.like("%" + email + "%"));
        }

        return builder;
    }

    /*
        query {
            getMember(memberId: 25) {
                userId
                ...
            }
        }
    * */
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GraphQLQuery(name = "getMember")
    public Member getMember(@GraphQLArgument(name = "memberId") Integer memberId) {
        return memberRepository.findById(memberId).get();
    }

    /*
        mutation {
            insertMember(member : {
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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GraphQLMutation(name = "insertMember")
    public int insertMember(@GraphQLArgument(name = "member") Member member) {
        member.setSecYn("N");
        member.setUseYn("Y");
        Integer memberId = memberRepository.save(member).getMemberId();
        if (memberId != null) return 1;
        else return 0;
    }

    /*
        mutation {
            updateMember(member : {
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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GraphQLMutation(name = "updateMember")
    public int updateMember(@GraphQLArgument(name = "member") Member member) {
        if (member.getMemberId() != null) {
            member.setSecYn("N");
            member.setUseYn("Y");
            memberRepository.save(member);
            return 1;
        }
        return 0;
    }

    /*
        mutation{
          deleteMember(memberId: 28)
        }
    * */
    @Transactional
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GraphQLMutation(name = "deleteMember")
    public int deleteMember(@GraphQLArgument(name = "memberId") Integer memberId) {
        if (memberId != null) {
            memberRepository.deleteById(memberId);
            return 1;
        }
        return 0;
    }
}
