package com.example.graphql.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpInpVo implements Serializable {
    private static final long serialVersionUID = -2925893922100802694L;

//    @ApiModelProperty(value = "아이디(이메일)", name = "userId")
    private String userId;

//    @ApiModelProperty(value = "비밀번호", name = "userPw")
    private String userPw;

//    @ApiModelProperty(value = "이름", name = "userNm")
    private String userNm;

//    @ApiModelProperty(value = "생년월일", name = "userBdDt")
    private String userBdDt;

//    @ApiModelProperty(value = "이메일 확인 코드", name = "emailVrfyCd")
    private String emailVrfyCd;

//    @ApiModelProperty(value = "로그인 유형 코드", name = "loginTypCd")
    private String loginTypCd;
}

