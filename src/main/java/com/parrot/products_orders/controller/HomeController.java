package com.parrot.products_orders.controller;

import com.parrot.products_orders.entity.DetalleOrden;
import com.parrot.products_orders.entity.Ordenes;
import com.parrot.products_orders.entity.Productos;
import com.parrot.products_orders.entity.Usuarios;
import com.parrot.products_orders.service.DetalleOrdenServices;
import com.parrot.products_orders.service.OrdenesServices;
import com.parrot.products_orders.service.ProductosService;
import com.parrot.products_orders.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductosService productosService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OrdenesServices ordenesServices;

    @Autowired
    private DetalleOrdenServices detalleOrdenServices;

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

        // Comprobar que productos seleccionados no se dupliquen
        Long idProducto=producto.getId();
        boolean ingresado= detalles.stream().anyMatch(prod -> prod.getProducto().getId()==idProducto);
        if(!ingresado) {
            detalles.add(detalleOrden);
        }

        sumaTotal= detalles.stream().mapToDouble(dt ->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        modelo.addAttribute("cart",detalles);
        modelo.addAttribute("orden",orden);

        return "usuario/carrito";
    }

    @GetMapping("/delete/cart/{id}")
    public String deleteProducto(@PathVariable Long id, Model modelo) {
        // Lista nueva de productos
        List<DetalleOrden> ordenNueva= new ArrayList<DetalleOrden>();

        for(DetalleOrden detalleOrden: detalles) {
            if(detalleOrden.getProducto().getId()!=id) {
                ordenNueva.add(detalleOrden);
            }
        }
        // Nueva lista con los productos restantes
        detalles=ordenNueva;

        double sumaTotal=0;
        sumaTotal= detalles.stream().mapToDouble(dt ->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        modelo.addAttribute("cart",detalles);
        modelo.addAttribute("orden",orden);

        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model modelo) {
        modelo.addAttribute("cart",detalles);
        modelo.addAttribute("orden",orden);
        return "usuario/carrito";
    }

    @GetMapping("/order")
    public String order(Model modelo) {
//        Usuarios usuario= usuarioService.findById().get();

        modelo.addAttribute("cart",detalles);
        modelo.addAttribute("orden",orden);
//        modelo.addAttribute("usuario", usuario)
        return "usuario/resumenorden";
    }

    @GetMapping("/saveOrder")
    public String saveOrder() {
        Date fecha_creacion= new Date();
        orden.setFecha_creacion(fecha_creacion);
        orden.setNumero(ordenesServices.generarNumeroOrden());

//        Usuarios usuario= usuarioService.findById().get();
//        orden.setUsuario(usuario);

        ordenesServices.save(orden);

        // Salvar detalles
        for (DetalleOrden dt:detalles) {
            dt.setOrden(orden);
            detalleOrdenServices.save(dt);
        }

        // Limpiar de la vista Orden
        orden= new Ordenes();
        detalles.clear();

        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model modelo) {
        List<Productos> productos= productosService.findAllProducts().stream().filter(n -> n.getNombre_producto().contains(nombre)).collect(Collectors.toList());
        modelo.addAttribute("productos",productos);
        return "usuario/home";
    }
}
