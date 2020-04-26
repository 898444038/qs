package com.chat.qs.enums;

/**
 * Created by Administrator on 2020/4/23 0023.
 */
public enum QuestionType {

    common(0,"普通问题"),
    weather(1,"天气问题"),
    deposit_change(2,"存款变动问题"),
    today_deposit(3,"今日存款日报问题"),
    card_quota(4,"信用卡指标问题"),

    ;

    private Integer code;
    private String name;

    QuestionType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
