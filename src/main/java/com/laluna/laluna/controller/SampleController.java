package com.laluna.laluna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/test")
    public String showTest() {
        return "/home/mypage";
    }

    @GetMapping("/test1")
    public String showTest1() {
        return "/home/mypagect";
    }
}
