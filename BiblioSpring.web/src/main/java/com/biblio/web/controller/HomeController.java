package com.biblio.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PreAuthorize("denyAll()") // se deniega el acceso globalmente
@RequestMapping("/saludo")
public class HomeController {

    // se autoriza este endpoint
    @PreAuthorize("permitAll()")
    @GetMapping("/hola")
    @ResponseBody
    public String hello(){
        return "HOLA DESDE SPRING NO SEGURO :(";
    }
    // se autoriza este endpoint cuando tenga la autorización CREATE
    @PreAuthorize("hasAuthority('CREATE')")
    @GetMapping("/hola/seguro")
    @ResponseBody
    public String helloSecured(){
        return "HOLA DESDE SPRING SEGURO :)";
    }

}
