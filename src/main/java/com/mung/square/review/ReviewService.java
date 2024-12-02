package com.mung.square.review;


import com.mung.square.dto.ReviewDTO;
import com.mung.square.dto.ReviewFileDTO;

import java.util.List;

//dao의 메소드를 호출
public interface ReviewService {
	//게시글등록  - tbreview테이블과 review_file테이블에 저장
	int insert(ReviewDTO review, List<ReviewFileDTO> reviewfiledtolist);
	int insert(ReviewDTO review);
	//게시글목록보기
	List<ReviewDTO> reviewList();
	//게시글상세조회
	ReviewDTO getReviewInfo(String review_no);
	//게시글수정
	int update(ReviewDTO review);
	//게시글삭제
	int delete(String review_no);
	//제목으로 검색
	List<ReviewDTO> search(String data);
	//제목,작성자, 본문, 작성일별로 검색
	List<ReviewDTO> search(String tag, String data);
	//category별로 검색하기
	List<ReviewDTO> findByCategory(String category);
	
	//게시글을 상세보기한 경우 보여질 업로드한 파일의 목록 조회
	List<ReviewFileDTO> getFileList(String reviewno);
	ReviewFileDTO getFile(String reviewFileno);
}
