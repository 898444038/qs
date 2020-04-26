package com.chat.qs.vo;

import com.chat.qs.entity.Category;


/**
 * 问题分类
 * @author: Administrator
 * @date: 2020-04-22
 */
public class CategoryVo extends Category {

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

