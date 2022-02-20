package com.parrot.products_orders.controller;

import com.parrot.products_orders.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductosService productosService;

    @GetMapping("")
    public String home(Model modelo) {
        modelo.addAttribute("productos", productosService.findAllProducts());
        return "usuario/home";
    }
}
