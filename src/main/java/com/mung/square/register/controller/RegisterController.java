package com.mung.square.register.controller;

import com.mung.square.dto.LoginDTO;
import com.mung.square.dto.UserDTO;
import com.mung.square.login.service.LoginService;
import com.mung.square.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping("/register")
    public String register(UserDTO registerUser, Model model) {
        System.out.println("스프링이 제공하는 로그인---------------------------------------");
         registerService.register(registerUser);
        return "redirect:/";
    }

}