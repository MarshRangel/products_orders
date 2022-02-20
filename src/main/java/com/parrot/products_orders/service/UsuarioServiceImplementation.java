package com.parrot.products_orders.service;

import com.parrot.products_orders.entity.Usuarios;
import com.parrot.products_orders.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImplementation implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuarios> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuarios save(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }
}
