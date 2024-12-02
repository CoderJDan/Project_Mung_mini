package com.mung.square.register.dao;

import com.mung.square.dto.LoginDTO;
import com.mung.square.dto.UserDTO;
import com.mung.square.login.dao.LoginDAO;
import com.mung.square.login.mapper.LoginMapper;
import com.mung.square.register.mapper.RegisterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RegisterDAOImpl implements RegisterDAO {
    private final RegisterMapper mapper;
    public void register(UserDTO registerUser) {
         mapper.register(registerUser);
    }

}
