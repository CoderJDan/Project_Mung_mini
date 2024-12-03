package com.mung.square.notice.mapper;

import com.mung.square.notice.domain.Comment;
import com.mung.square.notice.domain.Post;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper  // 추가
public interface PostMapper {
    // 게시글 목록 가져오기 (페이징 처리)
    List<Post> getAllPosts(@Param("offset") int offset, @Param("size") int size);

    Post getPostById(int postId);

    void createPost(Post post);

    void updatePost(Post post);

    void deletePost(int postId);
    // 검색 기능
    List<Post> searchPosts(@Param("keyword") String keyword,
                           @Param("offset") int offset,
                           @Param("size") int size);


    // 댓글 추가
    @Insert("INSERT INTO post_comment(post_comment_post_id, post_comment_content, post_comment_author_id, post_comment_created_at) " +
            "VALUES(#{postId}, #{comment.content}, #{comment.postAuthorId}, NOW())")
    void addComment(@Param("postId") int postId, @Param("comment") Comment comment);

    // 댓글 삭제
    @Delete("DELETE FROM post_comment WHERE post_comment_id = #{commentId}")
    void deleteComment(@Param("commentId") int commentId);
}
