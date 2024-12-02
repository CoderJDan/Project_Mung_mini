package com.mung.square.payment.service;

public interface UpdateService {
    boolean updateStatus(int orderNum);
    int updateReservationStatus(int orderNum);
}
