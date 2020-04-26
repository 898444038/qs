package com.chat.qs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("/index")
    public ModelAndView  index(){
        ModelAndView mav=new ModelAndView("socket");
        return mav;
    }

    @GetMapping("/")
    public String login(){
        return "index";
    }

    @GetMapping("/loading")
    public String loading(){
        return "index";
    }

    @GetMapping("/chat")
    public String chat(){
        return "chat";
    }
}