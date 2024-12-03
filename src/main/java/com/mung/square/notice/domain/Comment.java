package com.mung.square.notice.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Comment {
    private int commentId;
    private int postId;  // 게시글 ID
    private String commentContent;
    private String commentAuthor;
    private Timestamp createdAt;
}
