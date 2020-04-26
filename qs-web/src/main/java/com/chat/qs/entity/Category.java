package com.chat.qs.entity;

import com.ming.tools.generate.template.annotation.Generate;
import com.ming.tools.generate.template.annotation.database.Column;
import com.ming.tools.generate.template.annotation.database.Comment;
import com.ming.tools.generate.template.annotation.database.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 问题分类
 * Created by Administrator on 2020/4/13 0013.
 */
@Generate(isEffective = true,isCover = false,desc = "问题分类",tablePrefix = "qa",moduleName = "qs-web")
public class Category implements Serializable {

    @Column
    @PrimaryKey
    private Long id;

    @Column
    @Comment("分类名称")
    private String name;

    @Column
    @Comment("父级ID")
    private Long pid;

    @Column
    @Comment("创建时间")
    private Date createTime;

    @Column
    @Comment("删除标识(0：未删除，1：已删除)")
    private Boolean delFlag;

    @Comment("树结构")
    private List<Category> children;

    public Category() {
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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
