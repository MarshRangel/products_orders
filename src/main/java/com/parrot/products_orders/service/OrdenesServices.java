package com.parrot.products_orders.service;

import com.parrot.products_orders.entity.Ordenes;

import java.util.List;

public interface OrdenesServices {

    List<Ordenes> findAll();
    Ordenes save(Ordenes ordenes);
    String generarNumeroOrden();
}
