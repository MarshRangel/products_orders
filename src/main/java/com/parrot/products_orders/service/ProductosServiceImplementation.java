package com.parrot.products_orders.service;

import com.parrot.products_orders.entity.Productos;
import com.parrot.products_orders.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosServiceImplementation implements ProductosService {

    @Autowired
    private ProductosRepository productosRepository;

    @Override
    public Productos save(Productos producto) {
        return productosRepository.save(producto);
    }

    @Override
    public Optional<Productos> get(Long id) {
        return productosRepository.findById(id);
    }

    @Override
    public void update(Productos producto) {
        productosRepository.save(producto);
    }

    @Override
    public void delete(Long id) {
        productosRepository.deleteById(id);
    }

    @Override
    public List<Productos> findAllProducts() {
        return productosRepository.findAll();
    }
}
