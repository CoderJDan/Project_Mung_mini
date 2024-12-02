package com.mung.square.resv.service;

import com.mung.square.dto.ResvDTO;

public interface ResvService {
    boolean isReservationAvailable(ResvDTO resvDTO);  // 예약이 가능한지 확인
    void createReservation(ResvDTO resvDTO);           // 예약을 생성
}
