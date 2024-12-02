package com.mung.square.mypage.controller;

import com.mung.square.dto.DogDTO;
import com.mung.square.dto.ReservationForMypageDTO;
import com.mung.square.dto.UserDTO;
import com.mung.square.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@SessionAttributes("user")
public class MyPageController {
    private final MyPageService service;
    @Value("${mypage.upload.dir}")
    private String uploadDir;

    @GetMapping("")
    public String myPage(@ModelAttribute("user") UserDTO user, Model model) {
        if (user.getUserId().startsWith("m")) {
            return "redirect:/reservations";
        } else {
            List<DogDTO> dog = service.getDogList(user.getUserId());
            List<ReservationForMypageDTO> reservationList = service.getResv(user.getUserId());
            if (reservationList == null) {
                reservationList = new ArrayList<>(); // 빈 리스트로 초기화
            }

            model.addAttribute("doglist", dog);
            model.addAttribute("reservationlist", reservationList);
            return "include/mypageContent";

        }
    }

    @GetMapping("/profile/edit")
    public String myPageDogPro() {
        return "include/mypageupdateContent";
    }

    @PostMapping("/profile/edit")
    public String myPageEdit(@ModelAttribute("user") UserDTO user, Model model) {
        service.profileupdate(user);
        model.addAttribute("user", user);
        return "redirect:/mypage";
    }

    @PostMapping("/dog/edit")
    public String myPageEditDog(@ModelAttribute("dog") DogDTO dog, Model model, @RequestParam("dogProfileImage") MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                String storedFileName = service.uploadImage(file);
                dog.setImageUrl(storedFileName); // 업로드된 파일명을 DTO에 설정
            }
            service.updateDog(dog.getId(), dog); // DB 업데이트
            System.out.println("반려견 정보 수정 성공: " + dog);

        } catch (IOException e) {
            System.err.println("파일 업로드 실패: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("반려견 정보 수정 실패: " + e.getMessage());
        }
        return "redirect:/mypage";
    }

    @PostMapping("dogRegister")
    public String myPageDogReg(DogDTO dog, @RequestParam("dogProfileImage") MultipartFile file) {
        System.out.println(dog.toString());
        try {
            String storedFileName = service.uploadImage(file);
            //업로드 된 파일을 DogDTO에 저장
            dog.setImageUrl(storedFileName);
            service.insertDog(dog);
            System.out.println("파일업로드 성공::::" + service.uploadImage(file));

        } catch (IOException e) {
            System.out.println("파일 업로드 실패:::" + e.getMessage());
        }
        System.out.println("controller==============================" + dog);
        return "redirect:/mypage";
    }


}