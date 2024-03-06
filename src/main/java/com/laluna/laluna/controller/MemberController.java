package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.dto.board.UpdateBoardRequest;
import com.laluna.laluna.domain.dto.board.UpdateBoardResponse;
import com.laluna.laluna.domain.dto.pet.UpdatePetRequest;
import com.laluna.laluna.domain.dto.pet.UpdatePetResponse;
import com.laluna.laluna.domain.entity.Pets;
import com.laluna.laluna.service.PetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private void addLoginAttributes(MyUserDetails userDetails, Model model) {
        Long petnum = userDetails.getMnum();
        List<Pets> pets = petsService.findByMember(petnum);
        model.addAttribute("mypage", pets);
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

    @PostMapping("/mypage")
    public String updatepetpage(@AuthenticationPrincipal MyUserDetails userDetails,
                              @ModelAttribute UpdatePetRequest requestDTO, RedirectAttributes redirectAttributes) {
        Long petnum = userDetails.getMnum();
        UpdatePetResponse responseDTO = petsService.petUpdate(petnum, requestDTO);
        System.out.println(requestDTO);
        redirectAttributes.addFlashAttribute("mypage", responseDTO);
        return "/view/mypage";
    }


}
