package com.mung.square.notice.Controller;

import com.mung.square.notice.domain.Post;
import com.mung.square.notice.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/support/posts") // 경로 수정
public class PostController {

    @Autowired
    private PostService postService;



    // 게시글 작성

    @PostMapping("/submitNewPost")  // 경로 수정
    public String createPost(@RequestParam String title, @RequestParam String content , HttpSession session) {
        String authorName = (String) session.getAttribute("name");
        String postAuthorId = (String) session.getAttribute("userId");
        if (postAuthorId == null) {
            // 작성자 ID가 세션에서 가져오지 못했을 경우, 에러 페이지로 리다이렉트
            return "redirect:/error";
        }

        Post newPost = new Post();
        System.out.println(newPost);
        newPost.setPostTitle(title);
        newPost.setPostContent(content);
        newPost.setPostAuthorId(postAuthorId);
        newPost.setAuthorName(authorName);
        postService.createPost(newPost);


        return "redirect:/support/notice"; // 게시글 작성 후 목록 페이지로 리다이렉트
    }


    // 게시글 수정 페이지로 이동
    @GetMapping("/edit/{postId}")
    public String editPost(@PathVariable int postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        return "board/editPost";  // 게시글 수정 페이지 반환
    }

    // 게시글 수정 처리
    @PostMapping("/edit/{postId}")
    public String updatePost(@PathVariable int postId,
                             @RequestParam String title,
                             @RequestParam String content) {
        Post updatedPost = new Post();
        updatedPost.setPostId(postId);
        updatedPost.setPostTitle(title);
        updatedPost.setPostContent(content);
        postService.updatePost(postId, updatedPost);
        return "redirect:/support/notice";
    }

}
