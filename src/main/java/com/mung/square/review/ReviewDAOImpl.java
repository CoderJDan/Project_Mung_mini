package com.mung.square.review;

import com.mung.square.dto.ReviewDTO;
import com.mung.square.dto.ReviewFileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//DB를 액세스하기 위한 계층
//Mapper의 메소드를 호출

@Repository
@RequiredArgsConstructor
public class ReviewDAOImpl implements ReviewDAO {
    private final ReviewMapper mapper;

    @Override
    public int insert(ReviewDTO review) {
        return mapper.insert(review);
    }

    @Override
    public List<ReviewDTO> reviewList() {
        return mapper.selectall();
    }

    @Override
    public ReviewDTO read(String review_no) {
        return mapper.read(review_no);
    }

    @Override
    public int update(ReviewDTO review) {
        return mapper.update(review);
    }

    @Override
    public int delete(String review_no) {
        return mapper.delete(review_no); // 매퍼 호출
    }

    @Override
    public List<ReviewDTO> search(String data) {
        return List.of();
    }

    @Override
    public List<ReviewDTO> search(String tag, String data) {
        List<ReviewDTO> list = null;//결과를 저장할 변수
        Map<String,String> map = new HashMap<>();
        map.put("tag",tag);
        map.put("data",data);
        list = mapper.dynamicsearch(map);
        return list;
    }

    @Override
    public List<ReviewDTO> findByCategory(String category) {
        return mapper.categorySelect(category);
    }

    @Override
    public int insertFile(List<ReviewFileDTO> reviewfiledtolist) {
        System.out.println(reviewfiledtolist);
        return mapper.fileinsert(reviewfiledtolist);
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
