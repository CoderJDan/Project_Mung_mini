package com.mung.square.mypage.dao;

import com.mung.square.dto.DogDTO;
import com.mung.square.dto.ReservationForMypageDTO;
import com.mung.square.dto.UserDTO;

import java.util.List;

public interface MyPageDAO {
    UserDTO getUser(String id);
    List<DogDTO> getDogList(String id);
    DogDTO getDogById(String id);
    void insertDog(DogDTO dog);

    List<ReservationForMypageDTO> getResv(String id);

    ReservationForMypageDTO getResvByNum(String id);
    void profileupdate(UserDTO user);
    void updateDog(DogDTO dog);

}
