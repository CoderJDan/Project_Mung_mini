package com.mung.square.payment.dao;

import com.mung.square.payment.mapper.UpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UpdateDAOImpl implements UpdateDAO{
    private final UpdateMapper updateMapper;

    @Override
    public boolean updateStatus(int orderNum) {
        return updateMapper.updatePaymentStatus(orderNum) > 0;
    }

    @Override
    public int updateReservationStatus(int orderNum) {
        return updateMapper.updateReservationStatus(orderNum);
    }
}
