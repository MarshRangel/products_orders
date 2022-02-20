package com.parrot.products_orders.controller;

import com.parrot.products_orders.entity.Productos;
import com.parrot.products_orders.entity.Usuarios;
import com.parrot.products_orders.service.ProductosService;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

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
//        LOGGER.info("Producto {}", producto);
        productosService.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model modelo) {
        Productos producto= new Productos();
        Optional<Productos> optionalProducto=productosService.get(id);
        producto = optionalProducto.get();
//        LOGGER.info("Producto buscado: {}", producto);
        modelo.addAttribute("producto", producto);

        return "/productos/edit";
    }

    @PostMapping("update")
    public String update(Productos producto) {
        Productos p = new Productos();
        p = productosService.get(producto.getId()).get();
        producto.setUsuario(p.getUsuario());
        productosService.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(Long id) {
        productosService.delete(id);
        return "redirect:/productos";
    }
}
