package com.example.graphql.result;

public interface Result {

    public String getResultCode();

    public String getResultMsgId();

    public String[] getResultMsgArgs();

    public Result setMsgArgs(String resultMsgArgs);

    public Result setMsgArgs(String... resultMsgArgs);

}

