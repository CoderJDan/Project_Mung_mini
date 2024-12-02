package com.mung.square.register.mapper;

import com.mung.square.dto.LoginDTO;
import com.mung.square.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper {
    void register(UserDTO registerUser);

}
