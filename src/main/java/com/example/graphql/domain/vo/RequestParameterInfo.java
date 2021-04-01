package com.example.graphql.domain.vo;

import com.example.graphql.constant.Constant;
import lombok.Data;

import java.io.Serializable;

@Data
public class RequestParameterInfo implements Serializable {

    private static final long serialVersionUID = 662252409988642904L;

    private boolean pageable = false;

    public RequestParameterInfo() {
        this.pageNo = 1;
        this.pageSize = Constant.Page.DEF_PAGE_SIZE;
        this.sortKey = "";
        this.sortVal = "";
    }

    private Integer pageNo;
    private Integer pageSize;
    private String sortKey;
    private String sortVal;

}
