package com.mung.square.review;

import com.mung.square.dto.ReviewDTO;
import com.mung.square.dto.ReviewFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

//xml mapper 파일의 statement를 호출하는 역할
//=> 인터페이스로 만들어 놓으면 스프링내부에서 ReviewMapper를 구현하는 구현체를 만들어서 코드를 자동생성
//=> 자동으로 코드를 생성해주려면 @Mapper를 선언해야한다.
//=> 메소드명은 xml파일의 statement에 선언한 id명과 동일해야 한다.
@Mapper
public interface ReviewMapper {
    //xml에 정의된 id가 insert인 sql을 실행
    int insert(ReviewDTO review);
    List<ReviewDTO> selectall();
    List<ReviewDTO> categorySelect(String category);
    List<ReviewDTO> dynamicsearch(Map<String,String> map);
    ReviewDTO read(String reviewNo);
    int update(ReviewDTO review);
    int delete(String review_no);
    //파일insert
    int fileinsert(List<ReviewFileDTO> reviewFileDTOList);

}
