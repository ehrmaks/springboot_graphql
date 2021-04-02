package com.example.graphql.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "member_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "email"})
})
//@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 1)
public class Member {
    @JsonIgnore
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GraphQLQuery(name = "member_id")
    protected Integer memberId;

    // 아이디
    @Column(length = 30, nullable = false)
    @GraphQLQuery(name = "userId")
    protected String userId;

    // 패스워드
    @Column(length = 150, nullable = true)
    @GraphQLQuery(name = "passwd")
    protected String passwd;

    // 이름
    @Column(length = 20, nullable = false)
    @GraphQLQuery(name = "userName")
    protected String userName;

    // 이메일
    @Column(length = 100, nullable = false)
    @GraphQLQuery(name = "email")
    protected String email;

    // 가입일
    @CreationTimestamp
    @GraphQLQuery(name = "createDt")
    protected LocalDateTime createDt;

    // 수정일
    @UpdateTimestamp
    @GraphQLQuery(name = "updateDt")
    protected LocalDateTime updateDt;

    // 주소1
    @Column(length = 100, nullable = true)
    @GraphQLQuery(name = "address1")
    protected String address1;

    // 주소2
    @Column(length = 100, nullable = true)
    @GraphQLQuery(name = "address2")
    protected String address2;

    // 우편번호
    @Column(length = 20, nullable = true)
    @GraphQLQuery(name = "postNo")
    protected String postNo;

    // 프로필 이미지
    @Column(length = 1024)
    @GraphQLQuery(name = "profileImg")
    protected String profileImg;

    // 폰번호
    @Column(length = 30)
    @GraphQLQuery(name = "phoneNo")
    protected String phoneNo;

    // 사용여부
    @Column(length = 2)
    @GraphQLQuery(name = "useYn")
    protected String useYn;

    // 탈퇴여부
    @Column(length = 2)
    @GraphQLQuery(name = "secYn")
    protected String secYn;

    @JsonIgnore
    @Column(name = "activated")
    protected boolean activated;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    protected Set<Authority> authorities;
}