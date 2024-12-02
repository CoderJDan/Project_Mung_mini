package com.mung.square.review;

import com.mung.square.dto.ReviewDTO;
import com.mung.square.dto.ReviewFileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
//ReviewDAO의 메소드를 호출
//컨트롤러에서 받은 데이터를 가공해서 DAO로 넘기거나 DAO에서 받은 데이터를 가공해서 컨트롤러로 넘기는 작업
//비지니스로직
//트랜잭션처리
@Service
@RequiredArgsConstructor
//@RequiredArgsConstructor는 private final멤버들을 이용해서 생성자를 만들어서 제공
//빈을 찾아서 주입해주는 것까지 자동으로 처리
public class ReviewServiceImpl implements ReviewService {
    private final ReviewDAO reviewDAO;
    //파일업로드와 게시글이 함께 insert되는 게시글 등록서비스
    //메소드 하나에서 여러 개의 DAO메소드를 호출하는 경우 트랜잭션처리 해야한다.

    @Override
    public int insert(ReviewDTO review, List<ReviewFileDTO> reviewfiledtolist) {
        if (reviewfiledtolist.size() == 0) {
            reviewDAO.insert(review);
        }else {
            reviewDAO.insert(review);//게시글등록
            reviewDAO.insertFile(reviewfiledtolist);//첨부파일등록
        }
        return 0;
    }

    @Override
    public int insert(ReviewDTO review) {
        return reviewDAO.insert(review);
    }

    @Override
    public List<ReviewDTO> reviewList() {
        return reviewDAO.reviewList();
    }

    @Override
    public ReviewDTO getReviewInfo(String review_no) {
        return reviewDAO.read(review_no);
    }

    @Override
    public int update(ReviewDTO review) {
        return reviewDAO.update(review);
    }

    @Override
    public int delete(String review_no) {
        return reviewDAO.delete(review_no); // DAO 계층 호출
    }

    @Override
    public List<ReviewDTO> search(String data) {
        return List.of();
    }

    @Override
    public List<ReviewDTO> search(String tag, String data) {

        return reviewDAO.search(tag, data);
    }

    @Override
    public List<ReviewDTO> findByCategory(String category) {
        //category값을 판단해서 dao의 적절한 메소드를 호출
        List<ReviewDTO> list = null;
        if(category != null) {
            if(category.equals("all")) {
                list = reviewDAO.reviewList();
            }else{
                list = reviewDAO.findByCategory(category);
            }
        }
        return list;
    }

    @Override
    public List<ReviewFileDTO> getFileList(String reviewno) {
        return List.of();
    }

    @Override
    public ReviewFileDTO getFile(String reviewFileno) {
        return null;
    }
}
