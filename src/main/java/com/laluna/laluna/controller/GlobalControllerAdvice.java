package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addAttributes(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("loginId", userDetails.getUsername());
            model.addAttribute("loginRoles", userDetails.getAuthorities());
            model.addAttribute("loginMphone", userDetails.getMphone());
            model.addAttribute("loginAddress", userDetails.getAddress());
            model.addAttribute("loginEmail", userDetails.getEmail());
        }
    }
}
