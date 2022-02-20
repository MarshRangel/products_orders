package com.parrot.products_orders.controller;

import com.parrot.products_orders.entity.Productos;
import com.parrot.products_orders.entity.Usuarios;
import com.parrot.products_orders.service.ProductosService;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductosController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductosController.class);

    @Autowired
    private ProductosService productosService;

    @GetMapping("")
    public String show(Model modelo) {
        modelo.addAttribute("productos", productosService.findAllProducts());
        return "/productos/show";
    }

    @GetMapping("/create")
    public String create() {
        return "/productos/create";
    }

    @PostMapping("/save")
    public String save(Productos producto) {
        LOGGER.info("Producto {}", producto);
//        Usuarios u= new Usuarios(1,"","", "", "","","");
//        producto.setUsuario(u);
        productosService.save(producto);
        return "redirect:/productos";
    }
}
