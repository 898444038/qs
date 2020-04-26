package com.chat.qs.enums;

/**
 * Created by Administrator on 2020/4/23 0023.
 */
public enum WeatherType {

    sun("晴","sun","zwicon-sun","zwicon-sun"),
    cloudy("多云","wind","zwicon-cloudy-day","zwicon-sun"),
    yin("阴","wind","zwicon-cloud","zwicon-sun"),

    阵雨("阵雨","rain","zwicon-cloud","zwicon-sun"),
    雷阵雨("雷阵雨","thunder","zwicon-cloud","zwicon-sun"),
    雷阵雨伴有冰雹("雷阵雨伴有冰雹","thunder","zwicon-cloud","zwicon-sun"),
    雨夹雪("雨夹雪","","zwicon-cloud","zwicon-sun"),
    小雨("小雨","rain","zwicon-cloud","zwicon-sun"),
    中雨("中雨","rain","zwicon-cloud","zwicon-sun"),
    大雨("大雨","rain","zwicon-cloud","zwicon-sun"),
    暴雨("暴雨","rain","zwicon-cloud","zwicon-sun"),
    大暴雨("大暴雨","rain","zwicon-cloud","zwicon-sun"),
    特大暴雨("特大暴雨","rain","zwicon-cloud","zwicon-sun"),

    阵雪("阵雪","snow","zwicon-cloud","zwicon-sun"),
    小雪("小雪","snow","zwicon-cloud","zwicon-sun"),
    中雪("中雪","snow","zwicon-cloud","zwicon-sun"),
    大雪("大雪","snow","zwicon-cloud","zwicon-sun"),
    暴雪("暴雪","snow","zwicon-cloud","zwicon-sun"),

    雾("雾","","zwicon-cloud","zwicon-sun"),
    冻雨("冻雨","rain","zwicon-cloud","zwicon-sun"),
    沙尘暴("沙尘暴","wind","zwicon-cloud","zwicon-sun"),
    q1("小到中雨","rain","zwicon-cloud","zwicon-sun"),
    q2("中雨-大雨","rain","zwicon-cloud","zwicon-sun"),
    q3("大雨-暴雨","rain","zwicon-cloud","zwicon-sun"),
    q4("暴雨-大暴雨","rain","zwicon-cloud","zwicon-sun"),
    q5("大暴雨-特大暴雨","rain","zwicon-cloud","zwicon-sun"),

    x1("小雪-中雪","snow","zwicon-cloud","zwicon-sun"),
    x2("中雪-大雪","snow","zwicon-cloud","zwicon-sun"),
    x3("大雪-暴雪","snow","zwicon-cloud","zwicon-sun"),

    o1("浮尘","wind","zwicon-cloud","zwicon-sun"),
    o2("扬沙","wind","zwicon-cloud","zwicon-sun"),
    o3("强沙尘暴","wind","zwicon-cloud","zwicon-sun"),
    o4("霾","","zwicon-cloud","zwicon-sun"),
    ;

    private String name;
    private String type;
    private String dayIcon;
    private String nightIcon;

    WeatherType() {
    }

    WeatherType(String name,String type, String dayIcon, String nightIcon) {
        this.name = name;
        this.type = type;
        this.dayIcon = dayIcon;
        this.nightIcon = nightIcon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayIcon() {
        return dayIcon;
    }

    public void setDayIcon(String dayIcon) {
        this.dayIcon = dayIcon;
    }

    public String getNightIcon() {
        return nightIcon;
    }

    public void setNightIcon(String nightIcon) {
        this.nightIcon = nightIcon;
    }

    public static WeatherType getType(String name){
        for(WeatherType type : WeatherType.values()){
            if(type.getName().equals(name)){
                return type;
            }
        }
        return null;
    }
}
