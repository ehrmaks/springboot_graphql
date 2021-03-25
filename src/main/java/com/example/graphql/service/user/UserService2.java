//package com.example.graphql.service.user;
//
//import com.example.graphql.domain.model.JwtUser;
//import com.example.graphql.domain.repository.UserRepository;
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.core.types.Predicate;
//import io.leangen.graphql.annotations.GraphQLMutation;
//import io.leangen.graphql.annotations.GraphQLQuery;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Service
//@GraphQLApi
//@RequiredArgsConstructor
//public class UserService2 {
////    @Autowired
////    private userRepository userRepository;
//    @Autowired
//    private UserRepository userRepository;
//
////    @AuthenticationPrincipal
////    private JwtUser authCheck;
//
//
//
//    @PersistenceContext
//    private EntityManager em; // 영속성 객체를 자동으로 삽입해줌
//
//    /*
//        query {
//            getAllUsers {
//                memberNo
//                userId
//                ...
//            }
//        }
//    * */
//    @GraphQLQuery(name = "getAllUsers")
//    public List<JwtUser> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    /*
//        query {
//          getPagingUser(page: 1, size: 5) {
//            content{
//              memberNo
//              userId
//              profileImg
//              address1
//              address2
//            }
//            size
//            totalPages
//          }
//        }
//    * */
//    @GraphQLQuery(name = "getPagingUser")
//    public Page<JwtUser> getPagingUser(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size, Direction.DESC, "JwtUserNo");
//        return userRepository.findAll(pageable);
//    }
//
//    // d
//    /*
//        query {
//          getUserList(page:1, size:5, name: "jskim", email:"qwefk1234") {
//            memberNo
//            userId
//            name
//            email
//          }
//        }
//    * */
//    /*
//        query{
//          getUserList(page: 0, size: 5, name:"사나", email:"kjsfk") {
//            content {
//              userId
//            }
//            totalPages
//          }
//        }
//    * */
//    @GraphQLQuery(name = "getUserList")
//    public Page<JwtUser> getUserList(int page, int size, String name, String email) {
////        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//        // 멤버 Entity 경로를 가져옴
////        QJwtUser m = QJwtUser.JwtUser;
////        Predicate builder = predicate(name, email);
////
////        Map<String, Object> map = new HashMap<>();
////        int totalElements = userRepository.findAll().size();
////        int totalPage = (int) Math.ceil(totalElements / size);
////
////
////        List<JwtUser> list = queryFactory
////                                .selectFrom(m)
////                                .where(builder)
////                                .limit(size)
////                                .offset((page - 1) * size)
////                                .orderBy(m.JwtUserNo.desc())
////                                .fetch();
////
////        map.put("data", list);
////        map.put("totalElements", totalElements);
////        map.put("totalPage", totalPage);
////
////        return map;
//
//        // Page_JwtUser 생성
//        Pageable pageable = PageRequest.of(page, size, Direction.DESC, "JwtUserNo");
//
//        return userRepository.findAll(predicate(name, email), pageable);
//    }
//
//    // Predicate 필터 조건 설정
//    public Predicate predicate(String name, String email) {
//        QJwtUser m = QJwtUser.jwtUser;
//
//        // where 조건절의 동적 쿼리를 위한 셋팅
//        BooleanBuilder builder = new BooleanBuilder();
//
//        if (name != null) {
//            builder.and(m.name.like("%" + name + "%"));
//        }
//
//        if (email != null) {
//            builder.and(m.email.like("%" + email + "%"));
//        }
//
//        return builder;
//    }
//
//    /*
//        query {
//            getJwtUser(memberNo: 25) {
//                userId
//                ...
//            }
//        }
//    * */
//    @GraphQLQuery(name = "getUser")
//    public JwtUser getJwtUser(Integer memberNo) {
//        // 받는 파라미터명이 스키마의 변수명과 일치하여야 함.
//        return userRepository.findById(memberNo).get();
//    }
//
//    /*
//        mutation {
//            insertUser(member : {
//                address1: "seoul"
//                address2: "sillim"
//                email: "qwefk12345@naver.com"
//                name: "jskim"
//                passwd: "12345"
//                postNo: "02334"
//                userId: "ehrmaks1234"
//                profileImg: ""
//                phoneNo: "010-3524-0022"
//            })
//        }
//    * */
//    @Transactional
//    @GraphQLMutation(name = "insertUser")
//    public int insertUser(JwtUser JwtUser) {
//        JwtUser.setSecYn("N");
//        JwtUser.setUseYn("Y");
//        Integer memberNo = userRepository.save(JwtUser).getMemberNo();
//        if (memberNo != null) return 1;
//        else return 0;
//    }
//
//    /*
//        mutation {
//            updateUser(member : {
//                memberNo: 26
//                address1: "seoul"
//                address2: "sillim"
//                email: "qwefk1234@naver.com"
//                name: "jskim"
//                passwd: "1234"
//                postNo: "02334"
//                userId: "ehrmaks123"
//                profileImg: ""
//                phoneNo: "010-3524-0022"
//            })
//        }
//    * */
//    @Transactional
//    @GraphQLMutation(name = "updateUser")
//    public int updateUser(JwtUser JwtUser) {
////        if (authCheck != null) {
//            Integer memberNo = JwtUser.getMemberNo();
//            if (JwtUser.getMemberNo() != null) {
//                JwtUser.setSecYn("N");
//                JwtUser.setUseYn("Y");
//                userRepository.save(JwtUser);
//                return 1;
//            }
////        }
//        return 0;
//    }
//
//    /*
//        mutation{
//          deleteUser(memberNo: 28)
//        }
//    * */
//    @Transactional
//    @GraphQLMutation(name = "deleteUser")
//    public int deleteJwtUser(Integer JwtUserNo) {
//        if (JwtUserNo != null) {
//            userRepository.deleteById(JwtUserNo);
//            return 1;
//        }
//        return 0;
//    }
//}
