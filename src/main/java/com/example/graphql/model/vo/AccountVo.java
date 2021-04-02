package com.example.graphql.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * Redis Session에 들어가는 계정정보 Vo
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountVo implements Serializable {

    private static final long serialVersionUID = -1597106072309418199L;

    private String idToken;

    private String accessToken;

    private Integer id;
    private String loginId;
    private String userNm;
    private String[] role;

    //@ApiModelProperty(value = "생년월일", name = "userBdDt")
    private String userBdDt;

    //@ApiModelProperty(value = "Google 로그인 여부", name = "googleYn")
//    private String googleYn;

    //@ApiModelProperty(value = "핸드폰 통신사 코드", name = "mblApCd")
    private String mblApCd;

//    @ApiModelProperty(value = "핸드폰 번호", name = "mblNo")
    private String mblNo;

//    @ApiModelProperty(value = "이메일주소", name = "email")
    private String email;

    // 원래 있던값 QueryParameter 때문에 오류가 나서 일단 추가해놨다.
//    private String langCd;

    private String userId;

//    private String tenantId;
//
//    private String corpId;
//
//    private String corpReprLangCd;
//
//    private int roleLvl;
//
//    private String[] roles;
//
//    private String PrfrTzoneId;

}

