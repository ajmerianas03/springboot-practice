package com.springboot_practice.springboot_practice;

public class Response {

    private int qId;

    public long getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public String getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer;
    }

    private String useranswer;
}
