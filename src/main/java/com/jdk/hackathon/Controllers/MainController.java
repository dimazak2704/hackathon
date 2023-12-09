package com.jdk.hackathon.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/**")
    public String mainPage(){
        return "redirect:/help2need";
    }

    @GetMapping("/help2need")
    public String help2need(){
        return "main";
    }
}
