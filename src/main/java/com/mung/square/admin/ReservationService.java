package com.mung.square.admin;

import com.mung.square.dto.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationMapper reservationMapper;

    // 생성자를 명시적으로 선언
    public ReservationService(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    // 예약 상태 업데이트 메서드
    public boolean updateReservationStatus(String id, String status) {
        return reservationMapper.updateStatus(id, status) > 0;
    }

    // 모든 예약 가져오기 메서드
    public List<Reservation> getAllReservations() {
        return reservationMapper.getAllReservations();
    }

    // 필터 조건에 따른 예약 가져오기 메서드
    public List<Reservation> getFilteredReservations(String status, String startDate, String endDate) {
        return reservationMapper.getFilteredReservations(status, startDate, endDate);
    }
}
