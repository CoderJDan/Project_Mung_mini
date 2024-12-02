package com.mung.square.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpdateMapper {
    int updatePaymentStatus(int orderNum);
    int updateReservationStatus(int orderNum);
}
