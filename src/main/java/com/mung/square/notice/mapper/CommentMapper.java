package com.mung.square.notice.mapper;

import com.mung.square.notice.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper  // @Mapper 어노테이션 추가
public interface CommentMapper {

    // 댓글 목록 가져오기
    List<Comment> getCommentsByPostId(int postId);

    void createComment(Comment comment);

    void deleteComment(int commentId);
}
