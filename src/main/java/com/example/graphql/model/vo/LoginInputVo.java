package com.example.graphql.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginInputVo implements Serializable {
    private static final long serialVersionUID = -2925893922100802694L;

//    @ApiModelProperty(value = "로그안이이디", name = "email", example = "admin")
    private String email;

//    @ApiModelProperty(value = "패스워드", name = "password")
    private String password;
}
