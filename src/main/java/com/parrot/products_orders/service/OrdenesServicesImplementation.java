package com.parrot.products_orders.service;

import com.parrot.products_orders.entity.Ordenes;
import com.parrot.products_orders.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenesServicesImplementation implements OrdenesServices {

    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public Ordenes save(Ordenes orden) {
        return ordenRepository.save(orden);
    }
}
