package com.mung.square.register.service;

import com.mung.square.dto.UserDTO;
import com.mung.square.register.dao.RegisterDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final RegisterDAO registerDAO;
    @Override
    public void register(UserDTO registerUser) {
       registerDAO.register(registerUser);

    }
}
