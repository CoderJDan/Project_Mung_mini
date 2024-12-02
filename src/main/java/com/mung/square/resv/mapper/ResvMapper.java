package com.mung.square.resv.mapper;

import com.mung.square.dto.ResvDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResvMapper {
    // 예약 입력 리스트 받아줌
    ResvDTO getResv(Integer id);

    // 예약 중복을 처리하는 쿼리 메소드
    Integer checkOverlap(ResvDTO resvDTO);

    // 예약 입력을 받아주는 메소드
    void insertReservation(ResvDTO resvDTO);
}
