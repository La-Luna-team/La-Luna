package com.laluna.laluna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/test")
    public String showTest() {
        return "home/home";
    }
    @GetMapping("/test2")
    public String showTest2() {
        return "view/boardview";
    }
    @GetMapping("/test3")
    public String showTest3() {
        return "view/submit";
    }
    @GetMapping("/test4")
    public String showTest4() {
        return "view/mypage";
    }
    @GetMapping("/test5")
    public String showTest5() {
        return "view/mypagect";
    }
}
