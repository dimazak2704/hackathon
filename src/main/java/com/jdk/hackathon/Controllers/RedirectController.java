package com.jdk.hackathon.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {


    @GetMapping("/help2need")
    public String mainPage(){
        return "Main";
    }
}
