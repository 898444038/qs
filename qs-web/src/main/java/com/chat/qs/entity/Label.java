package com.chat.qs.entity;

import com.ming.tools.generate.template.annotation.Generate;
import com.ming.tools.generate.template.annotation.database.Column;
import com.ming.tools.generate.template.annotation.database.Comment;
import com.ming.tools.generate.template.annotation.database.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

/**
 * 问题标签
 */
@Generate(isEffective = true,isCover = false,desc = "问题标签",tablePrefix = "qa",moduleName = "qs-web")
public class Label implements Serializable {

    @Column
    @PrimaryKey
    private Long id;

    @Column
    @Comment("标签名")
    private String labelName;

    @Column
    @Comment("创建时间")
    private Date createTime;

    @Column
    @Comment("删除标识(0：未删除，1：已删除)")
    private Boolean delFlag;

    public Label() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}
