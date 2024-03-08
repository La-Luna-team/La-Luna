package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.dto.join.MemberAndPetDto;
import com.laluna.laluna.domain.dto.pet.UpdatePetRequest;
import com.laluna.laluna.service.MemberService;
import com.laluna.laluna.service.PetsService;
import com.laluna.laluna.service.RegisterMemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final RegisterMemberService registerMemberService;
    private final PetsService petsService;
    private final MemberService memberService;

    @PostMapping("/join")
    public String join(@RequestBody MemberAndPetDto dto) {
        System.out.println(dto.toString());  // 로깅
        try {
            registerMemberService.join(dto.getMemberid(), dto.getMemberpassword(), dto.getPhone(), dto.getAddress(), dto.getEmail(), dto.getMemberno());
            petsService.savePet(dto, dto.getMemberid());
            return "redirect:/view/mypage";
        } catch (Exception e) {
            return "redirect:/view/mypagect";
        }
    }
    Logger logger = LoggerFactory.getLogger(PetsService.class);

    @PostMapping("/update_pet_info")
    public String update_pet_info(@AuthenticationPrincipal MyUserDetails userDetails, UpdatePetRequest dto) {
        try {
            Long petnum = userDetails.getMemberno();
            petsService.petUpdate(petnum, dto);
            return "redirect:/view/mypage";
        } catch (Exception e) {
            return String.valueOf(ResponseEntity.badRequest().body(e.getMessage()));
        }
    }
    }
