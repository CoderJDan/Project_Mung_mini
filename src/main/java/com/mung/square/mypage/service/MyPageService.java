package com.mung.square.mypage.service;

import com.mung.square.dto.DogDTO;
import com.mung.square.dto.ReservationForMypageDTO;
import com.mung.square.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MyPageService {
    UserDTO getUser(String id);
    List<DogDTO> getDogList(String id);
    List<ReservationForMypageDTO> getResv(String id);
    ReservationForMypageDTO getResvByNum(String id);
    DogDTO getDogById(String id);
    void insertDog(DogDTO dog);
    String uploadImage(MultipartFile file) throws IOException;
    void profileupdate(UserDTO user);
    boolean updateDog(String dogId,DogDTO dog);
}
