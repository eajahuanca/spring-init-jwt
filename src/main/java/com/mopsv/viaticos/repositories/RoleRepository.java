package com.mopsv.viaticos.repositories;

import com.mopsv.viaticos.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByDenominacion(String denominacion);
}
