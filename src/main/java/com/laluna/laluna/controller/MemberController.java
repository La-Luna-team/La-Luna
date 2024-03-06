package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.entity.Pets;
import com.laluna.laluna.service.PetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public class MemberController {

    private final PetsService petsService;

    @GetMapping("/login")
    public String loginPage() {
        return "/view/login";
    }

    @GetMapping("/join")
    public String joinPage(){
        return "/view/join";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {

        Long mnum = userDetails.getmnum();
        List<Pets> pets = petsService.findPetsByMemberMid(mnum);
        System.out.println(pets);
        model.addAttribute("pets", pets);
        model.addAttribute("loginno", userDetails.getmnum());
        model.addAttribute("loginId", userDetails.getUsername());
        model.addAttribute("loginRoles", userDetails.getAuthorities());
        model.addAttribute("loginMphone", userDetails.getMphone());
        model.addAttribute("loginAddress", userDetails.getAddress());
        model.addAttribute("loginEmail", userDetails.getEmail());
        return "/view/dashboard";
    }
}
