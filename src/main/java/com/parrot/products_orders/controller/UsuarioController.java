package com.parrot.products_orders.controller;

import com.parrot.products_orders.entity.Usuarios;
import com.parrot.products_orders.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String create() {
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuarios usuario) {
        usuarioService.save(usuario);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @PostMapping("/acceder")
    public String acceder(Usuarios usuario, HttpStatus session) {
        Optional<Usuarios> user= usuarioService.findByUsername(usuario.getUsername());
//        if (user.isPresent()) {
//            session
//        }
        return "redirect:/";
    }
}
