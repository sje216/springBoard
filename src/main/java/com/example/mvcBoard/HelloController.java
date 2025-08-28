package com.example.mvcBoard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
