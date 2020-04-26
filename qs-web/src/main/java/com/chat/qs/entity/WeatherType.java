package com.chat.qs.entity;

import com.ming.tools.generate.template.annotation.Generate;
import com.ming.tools.generate.template.annotation.database.Column;
import com.ming.tools.generate.template.annotation.database.Comment;
import com.ming.tools.generate.template.annotation.database.PrimaryKey;

import java.io.Serializable;

/**
 * 阵雨\雷阵雨\浮尘\阵雪\大雨\小到中雨\小雪\中雨\小雨\晴\阴\雨夹雪\暴雨\多云
 * Created by Administrator on 2020/4/24 0024.
 */
@Generate(isEffective = true,isCover = false,desc = "天气类型",tablePrefix = "qa",moduleName = "qs-web")
public class WeatherType implements Serializable {

    @Column
    @PrimaryKey
    private Long id;

    @Column
    @Comment("中文名")
    private String name;

    @Column
    @Comment("英文名")
    private String enName;

    @Column
    @Comment("天气类型")
    private String type;

    @Column
    @Comment("白天天气图标")
    private String dayIcon;

    @Column
    @Comment("夜晚天气图标")
    private String nightIcon;

    public WeatherType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
