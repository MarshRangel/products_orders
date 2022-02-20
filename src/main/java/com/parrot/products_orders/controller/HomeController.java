package com.parrot.products_orders.controller;

import com.parrot.products_orders.entity.DetalleOrden;
import com.parrot.products_orders.entity.Ordenes;
import com.parrot.products_orders.entity.Productos;
import com.parrot.products_orders.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductosService productosService;

    // Detalles de la orden
    List<DetalleOrden> detalles= new ArrayList<DetalleOrden>();

    // Datos de la orden
    Ordenes orden= new Ordenes();

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
    public String addCart (@RequestParam Long id, @RequestParam Integer cantidad, Model modelo) {
        DetalleOrden detalleOrden= new DetalleOrden();
        Productos producto= new Productos();
        double sumaTotal=0;

        Optional<Productos> optionalProducto= productosService.get(id);
        producto= optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio_unitario());
        detalleOrden.setNombre_orden(producto.getNombre_producto());
        detalleOrden.setTotal(producto.getPrecio_unitario()*cantidad);
        detalleOrden.setProducto(producto);

        detalles.add(detalleOrden);

        sumaTotal= detalles.stream().mapToDouble(dt ->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        modelo.addAttribute("cart",detalles);
        modelo.addAttribute("orden",orden);

        return "usuario/carrito";
    }
}
