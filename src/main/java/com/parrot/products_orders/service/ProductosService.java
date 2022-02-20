package com.parrot.products_orders.service;


import com.parrot.products_orders.entity.Productos;

import java.util.List;
import java.util.Optional;

public interface ProductosService {

    public Productos save(Productos producto);
    public Optional<Productos> get(Long id);
    public void update(Productos producto);
    public void delete(Long id);

    public List<Productos> findAllProducts();
}
