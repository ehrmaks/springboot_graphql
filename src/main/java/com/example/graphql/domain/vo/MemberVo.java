package com.example.graphql.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@ToString
@Table(name = "member_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "email"})
})
//@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 1)
public class MemberVo {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @GraphQLQuery(name = "id")
    private Integer id;

    // 아이디
    @Column(length = 30, nullable = false)
    @GraphQLQuery(name = "userId")
    private String userId;

    // 패스워드
    @Column(length = 150, nullable = true)
    @GraphQLQuery(name = "passwd")
    private String passwd;

    // 이름
    @Column(length = 20, nullable = false)
    @GraphQLQuery(name = "userName")
    private String userName;

    // 이메일
    @Column(length = 100, nullable = false)
    @GraphQLQuery(name = "email")
    private String email;

    // 가입일
    @CreationTimestamp
    @GraphQLQuery(name = "createDt")
    private LocalDateTime createDt;

    // 수정일
    @UpdateTimestamp
    @GraphQLQuery(name = "updateDt")
    private LocalDateTime updateDt;

    // 주소1
    @Column(length = 100, nullable = true)
    @GraphQLQuery(name = "address1")
    private String address1;

    // 주소2
    @Column(length = 100, nullable = true)
    @GraphQLQuery(name = "address2")
    private String address2;

    // 우편번호
    @Column(length = 20, nullable = true)
    @GraphQLQuery(name = "postNo")
    private String postNo;

    // 프로필 이미지
    @Column(length = 1024)
    @GraphQLQuery(name = "profileImg")
    private String profileImg;

    // 폰번호
    @Column(length = 30)
    @GraphQLQuery(name = "phoneNo")
    private String phoneNo;

    // 사용여부
    @Column(length = 2)
    @GraphQLQuery(name = "useYn")
    private String useYn;

    // 탈퇴여부
    @Column(length = 2)
    @GraphQLQuery(name = "secYn")
    private String secYn;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities = new HashSet<>();
}