package com.zhannicholas.cpwms.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parts")
public class PartsController {

    @RequestMapping("")
    public String findAllParts(){
        return null;
    }
}
