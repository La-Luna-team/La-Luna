package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.dto.join.MemberAndPetDto;
import com.laluna.laluna.domain.dto.pet.UpdatePetRequest;
import com.laluna.laluna.domain.entity.Pets;
import com.laluna.laluna.service.PetsService;
import com.laluna.laluna.service.RegisterMemberService;
import groovy.util.logging.Log4j;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final RegisterMemberService registerMemberService;
    private final PetsService petsService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberAndPetDto dto) {
        try {
            registerMemberService.join(dto.getMemberid(), dto.getMemberpassword(), dto.getPhone(), dto.getAddress(), dto.getEmail(), dto.getMemberno());
            petsService.savePet(dto, dto.getMemberid());
            return ResponseEntity.ok("join success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addPet")
    public ResponseEntity<String> addPet(@ModelAttribute MemberAndPetDto dto) {
        try {
            // 현재 로그인한 사용자의 username을 가져온다
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            // 현재 로그인한 사용자에게 펫 정보를 추가한다
            petsService.addPet(dto, username);

            return ResponseEntity.ok("펫 추가 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("펫 추가 실패: " + e.getMessage());
        }
    }


    @PostMapping("/update_pet_info")
    public ResponseEntity<String> update_pet_info(@AuthenticationPrincipal MyUserDetails userDetails, UpdatePetRequest dto) {
        try {
            Long petnum = userDetails.getMemberno();
            petsService.petUpdate(petnum, dto);
            return ResponseEntity.ok("펫 정보 업데이트 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/pets/{petno}")
    public ResponseEntity<Void> deletePet(@PathVariable Long petno) {
        petsService.deletePet(petno);
        return ResponseEntity.noContent().build();
    }

}