package cn.edu.imufe.entity;

public class AnswerHistory {
    private Integer id;

    private Integer userId;

    private Integer answerId;

    private String userAnswers;

    private Integer questionStatus;

    public AnswerHistory() {}

    public AnswerHistory(Integer id, Integer userId, Integer answerId, String userAnswers, Integer questionStatus) {
        this.id = id;
        this.userId = userId;
        this.answerId = answerId;
        this.userAnswers = userAnswers;
        this.questionStatus = questionStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
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
}