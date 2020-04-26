package com.chat.qs.vo;

import com.chat.qs.entity.UnknownQuestion;


/**
 * 未知问题库
 * @author: Administrator
 * @date: 2020-04-23
 */
public class UnknownQuestionVo extends UnknownQuestion {

    private Integer pageNo;

    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}

