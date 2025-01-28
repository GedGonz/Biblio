package com.biblio.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/saludo")
public class HomeController {

    @GetMapping("/hola")
    @ResponseBody
    public String hello(){
        return "HOLA DESDE SPRING NO SEGURO :(";
    }

    @GetMapping("/hola/seguro")
    @ResponseBody
    public String helloSecured(){
        return "HOLA DESDE SPRING SEGURO :)";
    }

}
