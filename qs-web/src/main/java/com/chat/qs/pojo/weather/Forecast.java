package com.chat.qs.pojo.weather;

/**
 * 预测
 */
public class Forecast {

    private String date;//22日星期三
    private String high;//高温 20℃"
    private String fengxiang;//北风
    private String low;//低温 8℃"
    private String fengli;//<![CDATA[3-4级]]>
    private String type;//多云

    public Forecast() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "date='" + date + '\'' +
                ", high='" + high + '\'' +
                ", fengxiang='" + fengxiang + '\'' +
                ", low='" + low + '\'' +
                ", fengli='" + fengli + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
