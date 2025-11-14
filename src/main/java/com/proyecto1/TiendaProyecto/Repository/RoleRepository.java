package com.proyecto1.TiendaProyecto.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto1.TiendaProyecto.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNombre(String nombre);

    
} 