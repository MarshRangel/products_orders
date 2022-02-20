package com.parrot.products_orders.controller;

import com.parrot.products_orders.entity.Productos;
import com.parrot.products_orders.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


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

    @GetMapping("productohome/{id}")
    private String productoHome(@PathVariable Long id, Model modelo) {
        Productos producto = new Productos();
        Optional<Productos> productoOptional= productosService.get(id);
        producto = productoOptional.get();

        modelo.addAttribute("producto", producto);

        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart () {
        return "usuario/carrito";
    }
}
