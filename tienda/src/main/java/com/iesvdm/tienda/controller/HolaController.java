package com.iesvdm.tienda.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
public class HolaController {

    @GetMapping("/hola")
    public String holamundo() {
        return "¡Proyecto Spring Boot funcionando correctamente en VS Code!";
    }
    

    
    
}
