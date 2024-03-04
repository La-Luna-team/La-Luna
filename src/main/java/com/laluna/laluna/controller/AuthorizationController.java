package com.laluna.laluna.controller;

import com.laluna.laluna.domain.dto.join.MemberAndPetDto;
import com.laluna.laluna.domain.dto.member.MemberJoinDto;
import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.domain.entity.Pets;
import com.laluna.laluna.domain.entity.Photo;
import com.laluna.laluna.service.PetsService;
import com.laluna.laluna.service.RegisterMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final RegisterMemberService registerMemberService;
    private final PetsService petsService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinDto dto) {
        System.out.println(dto.toString());  // 로깅
        try {
            registerMemberService.join(dto.getMid(), dto.getMpw(), dto.getMphone(), dto.getAddress(), dto.getEmail());
            return ResponseEntity.ok("join success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
