package com.chat.qs.vo;

import com.chat.qs.entity.Question;


/**
 * 问题库
 * @author: Administrator
 * @date: 2020-04-22
 */
public class QuestionVo extends Question {

    private Integer pageNo = 1;

    private Integer pageSize = 10;

    private Integer fromIndex;

    private Integer toIndex;

    public Integer getFromIndex() {
        this.fromIndex = (pageNo-1)*pageSize;
        return fromIndex;
    }

    public void setFromIndex(Integer fromIndex) {
        this.fromIndex = fromIndex;
    }

    public Integer getToIndex() {
        this.toIndex = pageNo*pageSize;
        return toIndex;
    }

    public void setToIndex(Integer toIndex) {
        this.toIndex = toIndex;
    }

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

