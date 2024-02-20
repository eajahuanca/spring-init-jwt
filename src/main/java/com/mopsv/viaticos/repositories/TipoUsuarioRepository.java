package com.mopsv.viaticos.repositories;

import com.mopsv.viaticos.entities.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
    @Override
    Optional<TipoUsuario> findById(Long id);
    Optional<TipoUsuario> findByTipo(String tipo);
}
