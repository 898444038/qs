package com.chat.qs.pojo.weather;

import java.util.List;

/**
 * 天气
 */
public class Weather {

    private Yesterday yesterday;
    private String city;//南京
    private List<Forecast> forecast;
    private String ganmao;//感冒
    private String wendu;//温度
    private String aqi;

    public Weather() {
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public Yesterday getYesterday() {
        return yesterday;
    }

    public void setYesterday(Yesterday yesterday) {
        this.yesterday = yesterday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "yesterday=" + yesterday +
                ", city='" + city + '\'' +
                ", forecast=" + forecast +
                ", ganmao='" + ganmao + '\'' +
                ", wendu='" + wendu + '\'' +
                ", aqi='" + aqi + '\'' +
                '}';
    }
}
