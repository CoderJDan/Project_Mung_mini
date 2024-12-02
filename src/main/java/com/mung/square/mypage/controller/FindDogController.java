package com.mung.square.mypage.controller;

import com.mung.square.dto.DogDTO;
import com.mung.square.dto.ReservationForMypageDTO;
import com.mung.square.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequiredArgsConstructor
@RequestMapping("mypage")
public class FindDogController {
    private final MyPageService service;
    @Value("${mypage.upload.dir}")
    private String uploadDir;

    @GetMapping(value = "/dogProfile/{dogId}", produces = "application/json;charset=utf-8")
    public DogDTO myPageDogProfile(@PathVariable String dogId) {
        System.out.println("Getmapping");
        DogDTO dog = service.getDogById(dogId);
        System.out.println(dogId);
        System.out.println(dog);
        File file = new File(uploadDir + "/" + dog.getImageUrl());
//        dog.setImageUrl(file.getAbsolutePath());

        return dog;
    }
    @PutMapping("/dog/edit/{dogId}")
    public ResponseEntity<String> dogEdit(@PathVariable String dogId, @RequestBody DogDTO dog) {
        try {
            // 서비스 계층에서 반려견 정보 업데이트 처리
            boolean isUpdated = service.updateDog(dogId, dog);

            if (isUpdated) {
                return ResponseEntity.ok("반려견 정보가 성공적으로 수정되었습니다!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("반려견 정보를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            // 에러 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("반려견 정보를 수정하는 중 오류가 발생했습니다.");
        }

    }


    @GetMapping(value = "/reservation/{resvNum}", produces = "application/json;charset=utf-8")
    public ReservationForMypageDTO myPageDetailResv(@PathVariable String resvNum) {
        System.out.println("Getmapping//////////////////" + resvNum);
        ReservationForMypageDTO reservation = service.getResvByNum(resvNum);
        System.out.println(resvNum);
        System.out.println(reservation);
        return reservation;
    }

    @GetMapping("/user/signup/idck")
    public String myPageSignupIdck(@RequestParam("userId") String userId) {
        // 서비스에서 아이디 중복 여부 확인
        if (service.getUser(userId).toString() == null) {
            return null;
        } else return service.getUser(userId).toString();
    }



}
