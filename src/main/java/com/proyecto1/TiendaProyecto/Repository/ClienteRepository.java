package com.proyecto1.TiendaProyecto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto1.TiendaProyecto.Model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "CALL sp_listar_clientes()", nativeQuery = true)
    List<Cliente> listarClientes();

    @Modifying
    @Transactional
    @Query(value = "CALL sp_registrar_cliente(:p_nombre, :p_apellido, :p_email, :p_telefono, :p_direccion)", nativeQuery = true)
    Cliente registrarCliente(
        @Param("p_nombre") String nombre,
        @Param("p_apellido") String apellido,
        @Param("p_email") String email,
        @Param("p_telefono") String telefono,
        @Param("p_direccion") String direccion
    );

    @Modifying
    @Transactional
    @Query(value = "CALL sp_actualizar_cliente(:p_id, :p_nombre, :p_apellido, :p_email, :p_telefono, :p_direccion)", nativeQuery = true)
    Cliente actualizarCliente(
        @Param("p_id") Long id,
        @Param("p_nombre") String nombre,
        @Param("p_apellido") String apellido,
        @Param("p_email") String email,
        @Param("p_telefono") String telefono,
        @Param("p_direccion") String direccion
    );

    @Modifying
    @Transactional
    @Query(value = "CALL sp_eliminar_cliente(:p_id)", nativeQuery = true)
    Integer eliminarCliente(@Param("p_id") Long id);

    @Query(value = "CALL sp_buscar_cliente_por_id(:p_id)", nativeQuery = true)
    Cliente buscarPorId(@Param("p_id") Long id);
}
