package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.dto.join.MemberAndPetDto;
import com.laluna.laluna.domain.dto.member.MemberUpdateRequestDto;
import com.laluna.laluna.domain.dto.member.MemberUpdateResponseDto;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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


    @PostMapping("/update_member")
    public ResponseEntity<String> updateMember(@AuthenticationPrincipal MyUserDetails userDetails, MemberUpdateRequestDto requestDto) {
        try {
            String memberid = userDetails.getUsername();
            registerMemberService.memberUpdate(memberid, requestDto);

            // 세션 새로고침
            refreshSession();

            // 성공 응답 메시지
            return ResponseEntity.ok("회원 정보 업데이트 성공");
        } catch (Exception e) {
            // 예외 발생 시, 클라이언트에게 에러 메시지 반환
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{memberid}")
    public ResponseEntity<String> deleteMember(@PathVariable String memberid) {
        try {
            registerMemberService.deleteMember(memberid);

            // 세션 새로고침
            refreshSession();

            return ResponseEntity.ok("Member deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private void refreshSession() {
        // 현재 인증된 사용자의 Authentication 객체 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 인증 객체가 있고, 그 객체가 인증된 사용자라면
        if (authentication != null && authentication.isAuthenticated()) {
            // 현재 사용자의 세션 무효화
            ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession().invalidate();
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
    public ResponseEntity<Void> deletePet(@AuthenticationPrincipal MyUserDetails userDetails, @PathVariable Long petno) {
        try {
            String username = userDetails.getUsername();
            petsService.deletePet(username, petno);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}