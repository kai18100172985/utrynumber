package com.utry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/page")
public class ModelController {
    @GetMapping("/index")
    public String init(){       
        return "index";
    }
    
    @GetMapping("/login")
    public String Login(){       
        return "login";
    }

    @GetMapping("/register")
    public String register(){       
        return "register";
    }
    
    @RequestMapping("/error")
    public String error() {
    	return "error";
    }
    @RequestMapping("/user-add")
    public String add() {
    	return "views/user-add";
    }
    
    @RequestMapping("/user-list")
    public String list() {
    	return "views/user-list";
    }
    @RequestMapping("/user-exit")
    public String exit() {
    	return "views/user-exit";
    }
    
    @RequestMapping("/user-addexcel")
    public String addexcel() {
    	return "views/user-addexcel";
    }
    
    @RequestMapping("/number-get")
    public String memberget() {
    	return "views/number-get";
    }
    
    @RequestMapping("/number-list")
    public String memberlist() {
    	return "views/number-list";
    }
    
}