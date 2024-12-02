package com.mung.square.resv.service;

import com.mung.square.dto.ResvDTO;
import com.mung.square.resv.dao.ResvDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResvServiceImpl implements ResvService {

    private final ResvDAO resvDAO;

    @Autowired
    public ResvServiceImpl(ResvDAO resvDAO) {
        this.resvDAO = resvDAO;
    }

    @Override
    public boolean isReservationAvailable(ResvDTO resvDTO) {
        // 예약 시간이 겹치는지 확인
        return resvDAO.isTimeAvailable(resvDTO);
    }

    @Override
    public void createReservation(ResvDTO resvDTO) {
        // 예약을 삽입
        resvDAO.insertReservation(resvDTO);
    }
}
