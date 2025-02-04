package com.biblio.web.controller;

import com.biblio.domain.model.UserDto;
import com.biblio.domain.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("denyAll()") // se deniega el acceso globalmente
@RequestMapping("/saludo")
public class HomeController {

    final private UserService userService;
    public HomeController(UserService userService){
        this.userService=userService;
    }
    // se autoriza este endpoint
    @PreAuthorize("permitAll()")
    @GetMapping("/hola")
    public String hello(){
        var result=userService.getAll();
        return "HOLA DESDE SPRING NO SEGURO :( --> "+result.stream().map(UserDto::getUsername).reduce("",(acc, user)->acc+user+", ");
    }
    // se autoriza este endpoint cuando tenga la autorización CREATE
    @PreAuthorize("hasAuthority('CREATE')")
    @GetMapping("/hola/seguro")
    public String helloSecured(){
        return "HOLA DESDE SPRING SEGURO :)";
    }

}
