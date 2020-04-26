package com.chat.qs.entity;

import com.ming.tools.generate.template.annotation.Generate;
import com.ming.tools.generate.template.annotation.database.Column;
import com.ming.tools.generate.template.annotation.database.Comment;
import com.ming.tools.generate.template.annotation.database.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 回答
 * Created by Administrator on 2020/4/13 0013.
 */
@Generate(isEffective = true,isCover = false,desc = "答案库",tablePrefix = "qa",moduleName = "qs-web")
public class Answer implements Serializable {

    @Column
    @PrimaryKey
    private Long id;

    @Column
    @Comment("问题ID")
    private Long questionId;

    @Column
    @Comment("回答内容")
    private String content;

    @Column
    @Comment("创建时间")
    private Date createTime;

    @Column
    @Comment("删除标识(0：未删除，1：已删除)")
    private Boolean delFlag;

    @Comment("关联问题")
    private List<Question> questionList;

    public Answer() {
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
