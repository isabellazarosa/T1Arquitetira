package com.bcopstein.sistvendas.interfaceAdaptadora.controladores;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HomeController {
    @GetMapping("")
    public String welcomeMessage() {
        return "Bem vindo às lojas ACME";
    }
}
