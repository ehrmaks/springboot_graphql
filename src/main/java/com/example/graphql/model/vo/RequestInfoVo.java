package com.example.graphql.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestInfoVo implements Serializable {

    private static final long serialVersionUID = -3692574654151123489L;

    private String tenantId;

    private String language;

    private String accessToken;

    private String serviceId;

    private String apiKey;

    private AccountVo account;

}