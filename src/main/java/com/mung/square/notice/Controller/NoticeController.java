package com.mung.square.notice.Controller;

import com.mung.square.notice.domain.Post;
import com.mung.square.notice.service.PostService;
import com.mung.square.payment.controller.SessionUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/support/notice")
public class NoticeController {
    @Autowired
    private PostService postService;
    private SessionUtils model;





    // 게시글 수정
    @PostMapping("/updatePost/{postId}")
    public String updatePost(@PathVariable int postId, @RequestParam String title, @RequestParam String content) {
        try {
            Post post = new Post();
            post.setPostId(postId);
            post.setPostTitle(title);
            post.setPostContent(content);
            postService.updatePost(postId, post);  // 수정된 게시글을 DB에 반영
            return "redirect:/support/notice";  // 수정 후 목록 페이지로 리다이렉트
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // 오류 발생 시 에러 페이지로 이동
        }
    }


    // 게시글 검색 기능 추가
    @GetMapping("/search")
    public String searchPosts(@RequestParam String keyword,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size,
                              Model model) {
        List<Post> posts = postService.searchPosts(keyword, page, size);
        model.addAttribute("posts", posts);
        model.addAttribute("keyword", keyword);
        return "board/notice";  // 공지사항 목록 페이지 반환
    }


    // 게시글 상세보기
    @GetMapping("/viewpost/{postId}")
    public String viewPost(@PathVariable int postId, Model model) {
        Post post = postService.getPostById(postId);  // 해당 ID에 맞는 게시글 조회
        model.addAttribute("post", post);  // 게시글을 model에 담아서 Thymeleaf로 전달
        return "board/viewpost";  // 게시글 상세보기 페이지 반환
    }

    // 게시글 목록 조회
    @GetMapping
    public String getAllPosts(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size,
                              Model model) {
        List<Post> posts = postService.getAllPosts(page, size);
        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        return "board/notice";
    }
    // 딜리트
    @PostMapping("/deletePost/{postId}")
    public String deletePost(@PathVariable int postId) {
        try {
            postService.deletePostDirectly(postId);  // 게시글 삭제
            return "redirect:/support/notice";  // 삭제 후 목록 페이지로 리다이렉트
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // 오류 발생 시 에러 페이지로 이동
        }
    }

    // 새 글 작성 페이지로 이동
    @GetMapping("/newPost")
    public String newPost(HttpSession session) {
        String memberNo = (String) session.getAttribute("member_no"); // 세션에서 member_no 확인
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        if (memberNo != null && memberNo.startsWith("m")) {
            return "board/newPost";  // 매니저인 경우 글쓰기 페이지로 이동
        } else {
            return "redirect:/support/notice";  // 비회원은 게시판으로 리다이렉트
        }
    }
}

