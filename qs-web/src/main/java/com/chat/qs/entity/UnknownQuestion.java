package com.chat.qs.entity;

import com.ming.tools.generate.template.annotation.Generate;
import com.ming.tools.generate.template.annotation.database.Column;
import com.ming.tools.generate.template.annotation.database.Comment;
import com.ming.tools.generate.template.annotation.database.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

/**
 * 未知问题
 * Created by Administrator on 2020/4/13 0013.
 */
@Generate(isEffective = true,isCover = false,desc = "未知问题库",tablePrefix = "qa",moduleName = "qs-web")
public class UnknownQuestion implements Serializable {

    @Column
    @PrimaryKey
    private Long id;

    @Column
    @Comment("未知问题")
    private String title;

    @Column
    @Comment("是否解答：0未解答，1已解答，2已删除")
    private Integer status;

    @Column
    @Comment("创建时间")
    private Date createTime;

    @Column
    @Comment("解答内容")
    private String content;

    public UnknownQuestion() {
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
