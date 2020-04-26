package com.chat.qs.enums;

/**
 * Created by Administrator on 2020/4/17 0017.
 */
public enum CommonLanguage {
    NOT_FOUND_QUESTION("抱歉，小科还在学习这个问题呢！"),
    NOT_FOUND_ANSWER("抱歉!该问题没有说明!请联系管理员!"),
    ;

    private String desc;

    CommonLanguage(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
