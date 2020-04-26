package com.chat.qs.vo;

import com.chat.qs.entity.WeatherType;


/**
 * 天气类型
 * @author: Administrator
 * @date: 2020-04-24
 */
public class WeatherTypeVo extends WeatherType {

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

