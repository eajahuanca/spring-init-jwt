package com.mopsv.viaticos.services;

import com.mopsv.viaticos.entities.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();

    Usuario save(Usuario usuario);

    boolean existsByUsername(String username);
}
