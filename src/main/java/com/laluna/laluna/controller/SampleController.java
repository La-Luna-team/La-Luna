package com.laluna.laluna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/test")
    public String showTest() {
        return "view/home";
    }
    @GetMapping("/test2")
    public String showTest2() {
        return "view/mypage";
    }
    @GetMapping("/test3")
    public String showTest3() {
        return "view/mypagect";
    }
    @GetMapping("/test4")
    public String showTest4() {
        return "home/register";
    }
}
