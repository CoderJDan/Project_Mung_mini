package com.mung.square.mypage.service;

import com.mung.square.dto.DogDTO;
import com.mung.square.dto.ReservationForMypageDTO;
import com.mung.square.dto.UserDTO;
import com.mung.square.mypage.dao.MyPageDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {
    private final MyPageDAO myPageDAO;
    @Value("${mypage.upload.dir}")
    private String uploadDir;

    public UserDTO getUser(String id) {
        System.out.println("service,getUser");
        return myPageDAO.getUser(id);
    }

    public String uploadImage(MultipartFile file) throws IOException {
        // 업로드 디렉토리 생성
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //원본파일명을 추출
        String originalFilename = file.getOriginalFilename();
        //저장파일명생성
        String storeFileName = createStoreFilename(originalFilename);

        //transferTo 메소드는 지정된 경로의 파일명으로 파일객체를 생성
        file.transferTo(new File(getUploadpath(storeFileName)));

        return storeFileName;
    }

    public String getUploadpath(String filename) {
        // 파일 저장 경로 설정
        return uploadDir.endsWith("/") ? uploadDir + filename : uploadDir + "/" + filename;
    }

    private String createStoreFilename(String originalFilename) {
        //확장자를 추출
        int position = originalFilename.lastIndexOf(".");//.부터
        String ext = "";
        if (position != -1) { // 확장자가 있을 경우
            ext = originalFilename.substring(position + 1).toLowerCase(); // 확장자를 소문자로 변환
        }
        String uuid = UUID.randomUUID().toString();
        return ext.isEmpty() ? uuid : uuid + "." + ext;//ext가 없으면 그냥 uuid만 return

    }


    public List<DogDTO> getDogList(String id) {
        return myPageDAO.getDogList(id);
    }

    @Override
    public List<ReservationForMypageDTO> getResv(String id) {
        System.out.println("service,getResv========================>>>>>>>>>>>>>>>>>>>>");
        return myPageDAO.getResv(id);
    }

    public ReservationForMypageDTO getResvByNum(String id) {
        System.out.println(id);
        return myPageDAO.getResvByNum(id);
    }

    @Override
    public DogDTO getDogById(String id) {
        System.out.println(id);
        return myPageDAO.getDogById(id);
    }

    @Override
    public void insertDog(DogDTO dog) {
        dog.generateId();
        log.debug("Inserting data: {}", dog);
        log.debug("Inserting Dog in Service Layer: {}", dog);
        System.out.println("service,insertDog" + dog);
        myPageDAO.insertDog(dog);
    }

    @Override
    public void profileupdate(UserDTO user) {
        myPageDAO.profileupdate(user);
    }

    @Override
    public boolean updateDog(String dogId, DogDTO updatedDog) {
        DogDTO existingDog = myPageDAO.getDogById(dogId);
        if (existingDog != null) {
            existingDog.setName(updatedDog.getName());
            existingDog.setBreed(updatedDog.getBreed());
            existingDog.setGender(updatedDog.getGender());
            existingDog.setBirthDate(updatedDog.getBirthDate());
            existingDog.setWeight(updatedDog.getWeight());
            existingDog.setImageUrl(updatedDog.getImageUrl()); // 이미지 URL 업데이트

            myPageDAO.updateDog(existingDog); // 수정된 정보 저장
            return true;
        }
        System.out.println("false");
        return false; // 해당 ID의 반려견이 없을 경우
    }

}

