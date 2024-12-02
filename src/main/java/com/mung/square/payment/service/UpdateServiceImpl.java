package com.mung.square.payment.service;

import com.mung.square.payment.dao.UpdateDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateServiceImpl implements UpdateService{
    private final UpdateDAO updateDAO;

    @Override
    public boolean updateStatus(int orderNum) {
        return updateDAO.updateStatus(orderNum);
    }

    @Override
    public int updateReservationStatus(int orderNum) {
        return updateDAO.updateReservationStatus(orderNum);
    }
}
