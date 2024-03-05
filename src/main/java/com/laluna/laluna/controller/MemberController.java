package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class MemberController {

    @GetMapping("/login")
    public String loginPage() {
        return "/view/login";
    }

    @GetMapping("/join")
    public String joinPage(){
        return "/view/join";
    }

    private void addLoginAttributes(MyUserDetails userDetails, Model model) {

        model.addAttribute("loginId", userDetails.getUsername());
        model.addAttribute("loginRoles", userDetails.getAuthorities());
        model.addAttribute("loginMphone", userDetails.getMphone());
        model.addAttribute("loginAddress", userDetails.getAddress());
        model.addAttribute("loginEmail", userDetails.getEmail());
    }

    @GetMapping("/dashboard")
    public String dashboardPage(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
        addLoginAttributes(userDetails, model);
        return "/view/dashboard";
    }

    @GetMapping("/mypage")
    public String myPage(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
        addLoginAttributes(userDetails, model);
        return "/view/mypage";
    }


}
