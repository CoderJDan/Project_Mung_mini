package com.mung.square.payment.dao;

public interface UpdateDAO {
    boolean updateStatus(int orderNum);
    int updateReservationStatus(int orderNum);
}
