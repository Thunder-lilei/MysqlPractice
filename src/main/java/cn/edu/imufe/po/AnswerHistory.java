package cn.edu.imufe.po;

import java.util.Date;

public class AnswerHistory {
    private Long id;

    private Long userId;

    private Long answerId;

    private String userAnswers;

    private Integer questionStatus;

    private Date createTime;

    private Date updateTime;

    public AnswerHistory() {}

    public AnswerHistory(Long id, Long userId, Long answerId, String userAnswers, Integer questionStatus) {
        this.id = id;
        this.userId = userId;
        this.answerId = answerId;
        this.userAnswers = userAnswers;
        this.questionStatus = questionStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(String userAnswers) {
        this.userAnswers = userAnswers == null ? null : userAnswers.trim();
    }

    public Integer getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(Integer questionStatus) {
        this.questionStatus = questionStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}