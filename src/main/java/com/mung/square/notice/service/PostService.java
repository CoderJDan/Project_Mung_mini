
package com.mung.square.notice.service;

import com.mung.square.notice.domain.Comment;
import com.mung.square.notice.domain.Post;
import com.mung.square.notice.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PostMapper postMapper;

    // 게시글 목록 가져오기
    public List<Post> getAllPosts(int page, int size) {
        int offset = (page - 1) * size;
        return postMapper.getAllPosts(offset, size);
    }

    // 게시글 ID로 게시글 조회
    public Post getPostById(int postId) {
        String sql = "SELECT post_id, post_title, post_content, post_author, post_views, post_author_id, post_created_at " +
                "FROM post_table WHERE post_id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Post post = new Post();
            post.setPostId(rs.getInt("post_id"));
            post.setPostTitle(rs.getString("post_title"));
            post.setPostContent(rs.getString("post_content"));
            post.setPostAuthor(rs.getString("post_author"));
            post.setPostViews(rs.getInt("post_views"));
            post.setPostAuthorId(rs.getString("post_author_id"));
            post.setPostCreatedAt(rs.getTimestamp("post_created_at"));
            return post;
        }, postId);
    }

    // 새 게시글 작성
    public void createPost(Post post) {
        postMapper.createPost(post);  // 게시글 생성
    }

    // 게시글 수정
    public void updatePost(int postId, Post post) {
        post.setPostId(postId);  // 기존 ID로 게시글 수정
        postMapper.updatePost(post);  // 게시글 수정
    }
    // 게시글 삭제
    public void deletePostDirectly(int postId) {
        String sql = "DELETE FROM post_table WHERE post_id = ?";
        jdbcTemplate.update(sql, postId);
    }


    // 댓글 추가
    public void addComment(int postId, Comment comment) {
        postMapper.addComment(postId, comment);
    }

    // 댓글 삭제
    public void deleteComment(int commentId) {
        postMapper.deleteComment(commentId);
    }


    // 게시글 검색 및 페이징 기능 추가
    public List<Post> searchPosts(String keyword, int page, int size) {
        int offset = (page - 1) * size;
        String sql = "SELECT post_id, post_title, post_content, post_author, post_views, post_author_id, post_created_at " +
                "FROM post_table WHERE post_title LIKE ? OR post_content LIKE ? " +
                "LIMIT ? OFFSET ?";
        String searchKeyword = "%" + keyword + "%";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Post post = new Post();
            post.setPostId(rs.getInt("post_id"));
            post.setPostTitle(rs.getString("post_title"));
            post.setPostContent(rs.getString("post_content"));
            post.setPostAuthor(rs.getString("post_author"));
            post.setPostViews(rs.getInt("post_views"));
            post.setPostAuthorId(rs.getString("post_author_id"));
            post.setPostCreatedAt(rs.getTimestamp("post_created_at"));
            return post;
        }, searchKeyword, searchKeyword, size, offset);
    }

}
