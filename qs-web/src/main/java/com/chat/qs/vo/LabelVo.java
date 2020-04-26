package com.chat.qs.vo;

import com.chat.qs.entity.Label;


/**
 * 问题标签
 * @author: Administrator
 * @date: 2020-04-22
 */
public class LabelVo extends Label {

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

