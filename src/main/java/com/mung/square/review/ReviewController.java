package com.mung.square.review;

import com.mung.square.dto.ReviewDTO;
import com.mung.square.dto.ReviewFileDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/support/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService service;
    private final FileUploadService fileUploadService;
    //데이터베이스에서 조회한 게시글 목록을 출력
    //전체목록보기와 카테고리별로 조회하는 작업은 비슷한 작업이므로 컨트롤러를 같이 사용
    @GetMapping("/list")
    public String list(Model model,String category) {
        System.out.println("category=>"+category);
        //1. service의 메소드를 호출(비지니스메소드호출)
        List<ReviewDTO> reviewlist = service.findByCategory(category);
        //2. select작업은 db에서 받은 결과를 뷰로 넘겨주어야 하므로 데이터를 공유
        for (ReviewDTO review : reviewlist) {
            List<ReviewFileDTO> files = service.getFileList(review.getReviewNo());
            if (files != null) {
                for (ReviewFileDTO file : files) {
                    file.setFileUrl("/uploads/" + file.getStoreFilename());
                }
            }
            review.setFiles(files);
        }
        model.addAttribute("reviewlist",reviewlist);
        model.addAttribute("category",category);
        System.out.println(reviewlist);
        return "include/reviewlist";
    }

    //게시글등록폼을 forward 메소드
    @GetMapping("/write")
    public String writePage(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId"); // 세션에서 로그인 사용자 ID 가져오기
        if (userId == null) {
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }
        model.addAttribute("username", userId);
        return "review/review_write";
    }

    //입력한 데이터를 db에 저장하기
    @PostMapping("/write")
    public String insert(ReviewDTO review, HttpSession session) throws IOException {
        String userId = (String) session.getAttribute("userId"); // 세션에서 사용자 ID 가져오기
        review.setId(userId); // DTO에 작성자 설정
        //DTO에 담긴 MultipartFile객체들을 추출
        List<MultipartFile> file = new ArrayList<>();
        if (review.getFiles() != null) {
            for (ReviewFileDTO reviewFile : review.getFiles()) {
                // 필요 시 MultipartFile로 변환 (여기선 필요하지 않다면 단순히 처리)
                System.out.println("File URL: " + reviewFile.getFileUrl());
            }
        }
       List<ReviewFileDTO> reviewfilelist =  fileUploadService.uploadFiles(file);
        System.out.println(reviewfilelist);
        service.insert(review,reviewfilelist);
        //뷰로 forward하지 않고 글쓰기가 완료되면 목록보기로 가기 위해서 컨트롤러로 redirect
        //reviewlist.html파일에 출력할 데이터는 컨트롤러를 거쳐야 발생하는 데이터이므로 무조건
        //컨트롤러를 실행한다.
        return "redirect:/support/review/list?category=all";
    }
    //동적쿼리를 테스트
    //사용자가 select에서 어떤 option을 선택하고 작업하냐에 따라 다른 sql문이 만들어진다.
    //                                                       -------
    //                                                      where절
    @PostMapping("/search")
    public String search(String tag,String search,Model model){
        System.out.println(tag+"-------"+search);
        List<ReviewDTO> reviewList = service.search(tag,search);
        model.addAttribute("reviewlist",reviewList);
        return "include/reviewlist";
    }
    @GetMapping("/search")
    public String showSearchPage(Model model) {
        // 초기 검색 페이지 렌더링
        return "searchPage";
    }
    @GetMapping("/read")
    public String read(String reviewNo,String action,Model model){
        //1. 비지니스메소드 호출
        ReviewDTO review = service.getReviewInfo(reviewNo);
        System.out.println("==============================");
        System.out.println(review);
        String view="";
        //2. 데이터공유
        model.addAttribute("review",review);
        //3. action에 따라서 뷰를 다르게 정의
        if(action.equals("READ")){
            view="review/review_read";
        }else{
            view="review/review_update";
        }
        return view;
    }
    //업데이트
    @PostMapping("/update")
    public String update(ReviewDTO review){
        System.out.println(review);
        service.update(review);
        //수정이 끝나면 게시글목록보기
        return "redirect:/support/review/list?category=all";
    }
    @GetMapping("/update")
    public String showUpdateForm(String reviewNo, Model model) {
        ReviewDTO review = service.getReviewInfo(reviewNo);
        model.addAttribute("review", review);
        return "review/review_update";
    }
    @PostMapping("/delete")
    public String delete(String reviewNo) {
        System.out.println("Deleting review with reviewNo: " + reviewNo);
        service.delete(reviewNo); // 서비스 계층 호출
        return "redirect:/support/review/list?category=all"; // 목록 페이지로 리다이렉트
    }
    @GetMapping("/delete")
    public String showDeleteErrorPage() {
        return "errorPage"; // 에러 페이지 또는 경고 메시지 표시
    }

}
