package com.chat.qs.entity;

import com.ming.tools.generate.template.annotation.Generate;
import com.ming.tools.generate.template.annotation.database.Column;
import com.ming.tools.generate.template.annotation.database.Comment;
import com.ming.tools.generate.template.annotation.database.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 城市
 * Created by Administrator on 2020/4/13 0013.
 */
@Generate(isEffective = true,isCover = false,desc = "城市",tablePrefix = "qa",moduleName = "qs-web")
public class City implements Serializable {

    @Column
    @PrimaryKey
    @Comment("编码")
    private Long id;

    @Column
    @Comment("名称")
    private String name;

    public City() {
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
}
