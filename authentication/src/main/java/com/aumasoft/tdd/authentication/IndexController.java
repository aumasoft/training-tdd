package com.aumasoft.tdd.authentication;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping({ "/", "/index"})
    public ModelAndView index() {
        return new ModelAndView("index", Map.of("welcome", "Welcome to Spring"));
    }
}
