package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.dto.join.MemberAndPetDto;
import com.laluna.laluna.domain.dto.pet.ReadPetResponse;
import com.laluna.laluna.domain.entity.Pets;
import com.laluna.laluna.service.PetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class PetsController {

    private final PetsService petsService;

    @GetMapping("/mypage")
    public String mypetPage(@AuthenticationPrincipal MemberAndPetDto dto, Model model) {
        String mid = dto.getMid();
        Pets petDTO = petsService.savePet(dto, mid);
        model.addAttribute("mypage", petDTO);
        return "/view/mypage";
    }
}
