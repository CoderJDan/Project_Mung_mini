package com.mung.square.login.controller;

import com.mung.square.dto.LoginDTO;
import com.mung.square.dto.UserDTO;
import com.mung.square.login.service.LoginService;
import jakarta.servlet.http.HttpSession;
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
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String showLoginPage() {
        // 로그인 페이지 렌더링
        return "redirect:/"; // login.html 템플릿을 반환
    }

    @PostMapping("/login")
    public String login(LoginDTO login, Model model, HttpSession session) {
        System.out.println("스프링이 제공하는 로그인---------------------------------------");
        UserDTO user = loginService.login(login);

        session.setAttribute("userId", user.getUserId());
        model.addAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {//세션 종료
        System.out.println("스프링이 제공하는 로그아웃-------------------------------------");
        sessionStatus.setComplete();
        //@SessionAttributes와 함계 사용....세션에 있는 로그인한 정보 객체를 제거 - @SeeeionAttributes에 정의된 이름의 어트리부트
        return "redirect:/";
    }
    //SessionStatus는 내부에서
//    세션의 상테를 학인할수 있는 객체
    //@SessionAttribute로 로그인한 유저 정보를 관리하는 경우

    //@ModelAttreibute("user")라고 정의하면 모델에 있는 대이터ㅇ를 매개변수에 매필할떄
    //=> model객체나 세션에 저장되너있는 어트리부ㅜ틀 메소드의 매개변수로 바인딩하는 경우사용
    //=> user라는 이름으로 세션에 저장되어 있는 객체가 메소드가 호출되면서 매핑되도록 처리
    /*@GetMapping("/mypage")
    public String mypage(@ModelAttribute("user") MemberDTO user) {
        //로그인한 사용자의 정보
        System.out.println("로그인한 사용자의 정보");
        return "emp/mypage";
    }*/

}
