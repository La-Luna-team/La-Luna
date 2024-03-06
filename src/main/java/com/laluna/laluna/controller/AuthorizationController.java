package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.dto.join.MemberAndPetDto;
import com.laluna.laluna.domain.dto.pet.UpdatePetRequest;
import com.laluna.laluna.service.PetsService;
import com.laluna.laluna.service.RegisterMemberService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberAndPetDto dto) {
        System.out.println(dto.toString());  // 로깅
        try {
            registerMemberService.join(dto.getMid(), dto.getMpw(), dto.getMphone(), dto.getAddress(), dto.getEmail(), dto.getMnum());
            petsService.savePet(dto, dto.getMid());
            return ResponseEntity.ok("join success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update_pet_info")
    public ResponseEntity<String> update_pet_info(@AuthenticationPrincipal MyUserDetails userDetails, UpdatePetRequest dto) {
        try {
            Long petnum = userDetails.getMnum();
            petsService.petUpdate(petnum, dto); // 펫 정보 업데이트 서비스 호출
            return ResponseEntity.ok("펫 정보 업데이트 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
