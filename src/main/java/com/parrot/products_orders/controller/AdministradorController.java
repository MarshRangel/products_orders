package com.parrot.products_orders.controller;

import com.parrot.products_orders.entity.Productos;
import com.parrot.products_orders.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private ProductosService productosService;

    @GetMapping("")
    public String home(Model modelo){

        List<Productos> productos = productosService.findAllProducts();
        modelo.addAttribute("productos", productos);

        return "administrador/home";
    }
}
