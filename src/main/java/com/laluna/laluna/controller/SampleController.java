package com.laluna.laluna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/test")
    public String showTest() {
        return "view/home";
    }
    @GetMapping("/test1")
    public String showTest1() {
        return "view/list";
        return "home/register";
    }
}
