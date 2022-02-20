package com.parrot.products_orders.service;

import com.parrot.products_orders.entity.Usuarios;

import java.util.Optional;

public interface UsuarioService {

    Optional<Usuarios> findById(Long id);
    Usuarios save(Usuarios usuario);
}
