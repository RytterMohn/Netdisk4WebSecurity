package com.wdd.studentmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InjectionController {
    @GetMapping("/execute")
    public String execute() {
        return "injection/CommandInjection";
    }
}

