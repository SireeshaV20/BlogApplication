package com.application.recipeblog.dto;

public class CommentDTO {
    private Long id;
    private String text;
    private UserCommentDTO userDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserCommentDTO getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserCommentDTO userCommentDTO) {
        this.userDetails = userCommentDTO;
    }
}