package com.chat.qs.pojo.weather;

/**
 * Created by Administrator on 2020/4/23 0023.
 */
public class ResultMsg {

    private Weather data;
    private Integer status;//1000
    private String desc;//OK

    public ResultMsg() {
    }

    public Weather getData() {
        return data;
    }

    public void setData(Weather data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ResultMsg{" +
                "data=" + data +
                ", status=" + status +
                ", desc='" + desc + '\'' +
                '}';
    }
}
