package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.dto.board.ReadBoardResponse;
import com.laluna.laluna.domain.dto.join.MemberAndPetDto;
import com.laluna.laluna.domain.dto.pet.ReadPetResponse;
import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.domain.entity.Pets;
import com.laluna.laluna.service.MemberService;
import com.laluna.laluna.service.PetsService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public abstract class PetsController {

    private final PetsService petsService;

//    @GetMapping("/mypage")
//    public String mypetPage(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
//        Long petnum = userDetails.getMnum();
//        List<Pets> pets = petsService.findByMember(petnum);
//        model.addAttribute("mypage", pets);
//        return "/view/mypage";
//    }

}
