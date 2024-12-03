package com.mung.square.notice.service;

import com.mung.square.notice.domain.Comment;
import com.mung.square.notice.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CommentMapper commentMapper;
    // 특정 게시글의 댓글 가져오기
    public List<Comment> getCommentsByPostId(int postId) {
        return commentMapper.getCommentsByPostId(postId);
    }

    // 댓글 작성
    public void createComment(int postId, Comment comment) {
        comment.setPostId(postId);  // 게시글 ID와 댓글 연결
        commentMapper.createComment(comment);  // 댓글 DB에 저장
    }

    // 댓글 삭제
    public void deleteComment(int commentId) {
        commentMapper.deleteComment(commentId);  // 댓글 DB에서 삭제
    }
    // 삭제
    public void deletePostById(int postId) {
        String sql = "DELETE FROM post_table WHERE post_id = ?";
        jdbcTemplate.update(sql, postId);
    }

}
