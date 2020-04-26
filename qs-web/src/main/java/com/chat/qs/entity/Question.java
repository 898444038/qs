package com.chat.qs.entity;

import com.chat.qs.pojo.QuestionScore;
import com.ming.tools.generate.template.annotation.Generate;
import com.ming.tools.generate.template.annotation.database.Column;
import com.ming.tools.generate.template.annotation.database.Comment;
import com.ming.tools.generate.template.annotation.database.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 问题
 * Created by Administrator on 2020/4/13 0013.
 */
@Generate(isEffective = true,isCover = false,desc = "问题库",tablePrefix = "qa",moduleName = "qs-web")
public class Question implements Serializable {

    @Column
    @PrimaryKey
    private Long id;

    @Column
    @Comment("问题")
    private String title;

    @Column
    @Comment("问题类型:0普通问题 1:特殊问题")
    private Integer type;

    @Column
    @Comment("创建时间")
    private Date createTime;

    @Column
    @Comment("删除标识(0：未删除，1：已删除)")
    private Boolean delFlag;

    @Column
    @Comment("分类ID")
    private Long cateId;

    @Comment("关联答案")
    private List<Answer> answerList;

    @Comment("关联分类")
    private Category category;

    @Comment("关联标签")
    private List<Label> labelList;

    @Comment("分词结果")
    private Set<String> wordSet;

    @Comment("评分")
    private QuestionScore questionScore;

    public Question() {
    }

    public Question(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    public QuestionScore getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(QuestionScore questionScore) {
        this.questionScore = questionScore;
    }

    public Set<String> getWordSet() {
        return wordSet;
    }

    public void setWordSet(Set<String> wordSet) {
        this.wordSet = wordSet;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
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

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Label> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<Label> labelList) {
        this.labelList = labelList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", delFlag=" + delFlag +
                ", answerList=" + answerList +
                ", category=" + category +
                ", labelList=" + labelList +
                ", wordList=" + wordSet +
                '}';
    }
}
