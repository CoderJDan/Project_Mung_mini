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
        List<DogDTO> dog = service.getDogList(user.getUserId());
        List<ReservationForMypageDTO> reservationList = service.getResv(user.getUserId());
        model.addAttribute("doglist", dog);
        model.addAttribute("reservationlist", reservationList);
        return "include/mypageContent";
    }

    @GetMapping("/dogRegister")
    public String myPageDogPro() {
        return "menu/dogRegister";
    }

    @GetMapping("/dogProfile")
    public String myPageDogReg() {
        return "menu/dogProfile";
    }
    @PostMapping("dogRegister")
    public String myPageDogReg(DogDTO dog,@RequestParam("dogProfileImage") MultipartFile file){
        System.out.println(dog.toString());
        try {
            String storedFileName = service.uploadImage(file);
            //업로드 된 파일을 DogDTO에 저장
            dog.setImageUrl(storedFileName);
            service.insertDog(dog);
            System.out.println( "파일업로드 성공::::"+service.uploadImage(file));

        } catch (IOException e) {
            System.out.println( "파일 업로드 실패:::"+e.getMessage());
        }
        System.out.println("controller=============================="+dog);
        return "redirect:/mypage";
    }


}