package com.mung.square.mypage.dao;

import com.mung.square.dto.DogDTO;
import com.mung.square.dto.ReservationForMypageDTO;
import com.mung.square.dto.UserDTO;
import com.mung.square.mypage.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyPageDAOImple implements MyPageDAO {
    private final MyPageMapper mapper;


    @Override
    public UserDTO getUser(String id) {
        return mapper.getuser(id);
    }

    @Override
    public List<DogDTO> getDogList(String id) {
        return mapper.getdoglist(id);
    }


    @Override
    public DogDTO getDogById(String id) {
        System.out.println("DAO" + id);
        return mapper.getdogbyid(id);
    }

    @Override
    public List<ReservationForMypageDTO> getResv(String id) {
        return mapper.getresv(id);
    }

    @Override
    public ReservationForMypageDTO getResvByNum(String id) {
        return mapper.getresvbyid(id);
    }


    @Override
    public void insertDog(DogDTO dog) {
        mapper.insertdog(dog);
    }

    @Override
    public void profileupdate(UserDTO user) {
        mapper.profileupdate(user);
    }

    @Override
    public void updateDog(DogDTO dog) {
        mapper.updatedog(dog);
    }

}
