package com.chat.qs.vo;

import com.chat.qs.entity.City;


/**
 * 城市
 * @author: Administrator
 * @date: 2020-04-23
 */
public class CityVo extends City {

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

