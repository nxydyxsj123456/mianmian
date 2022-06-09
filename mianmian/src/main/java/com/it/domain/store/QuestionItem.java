package com.it.domain.store;

import javax.swing.*;

public class QuestionItem {
    private String id;
    private String questionId;
    private String content;
    private String picture;
    private String isRight;

    public QuestionItem() {
    }

    public QuestionItem(String id, String questionId, String content, String picture, String isRight) {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
        this.picture = picture;
        this.isRight = isRight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }

    @Override
    public String toString() {
        return "QuestionItem{" +
                "id='" + id + '\'' +
                ", questionId='" + questionId + '\'' +
                ", content='" + content + '\'' +
                ", picture='" + picture + '\'' +
                ", isRight='" + isRight + '\'' +
                '}';
    }
}
