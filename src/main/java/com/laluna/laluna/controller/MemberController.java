package com.laluna.laluna.controller;

import com.laluna.laluna.domain.dto.member.ReadMemberResponse;
import com.laluna.laluna.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage() {
        return "/view/login";
    }

    @GetMapping("/join")
    public String joinPage(){
        return "/view/join";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("loginId", user.getUsername());
        model.addAttribute("loginRoles", user.getAuthorities());
        return "/view/dashboard";
    }
    @GetMapping("/boardview")
    public String memberinfo(@PathVariable Long mnum, Model model){
        ReadMemberResponse responseDTO = memberService.readMember(mnum);
        model.addAttribute("mdto",responseDTO);
        return "boardview";
    }
}
