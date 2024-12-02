package com.mung.square.review;


import com.mung.square.dto.ReviewDTO;
import com.mung.square.dto.ReviewFileDTO;

import java.util.List;

//mybatis의 SqlSession을 이용해서 작업
public interface ReviewDAO {
	//게시글등록 - db에 처리
	int insert(ReviewDTO review);
	//게시글목록보기 - db에 처리
	List<ReviewDTO> reviewList();
	//게시글상세조회 - db에 처리
	ReviewDTO read(String review_no);
	//게시글수정 - db에 처리
	int update(ReviewDTO review);
	//게시글삭제 - db에 처리
	int delete(String review_no);
	//제목으로 검색
	List<ReviewDTO> search(String data);
	//제목,작성자, 본문, 작성일별로 검색
	List<ReviewDTO> search(String tag, String data);
	//category별로 검색
	List<ReviewDTO> findByCategory(String category);
	
	//=================첨부파일을 관리하기 위한 기능=========================
	
	//첨부파일을 저장하기 위한 메소드	
	int insertFile(List<ReviewFileDTO> reviewfiledtolist);
//	//게시글을 상세보기한 경우 보여질 업로드한 파일의 목록 조회
	List<ReviewFileDTO> getFileList(String reviewno);
	//파일정보를 리턴
	ReviewFileDTO getFile(String reviewFileno);
}




