package com.proyecto1.TiendaProyecto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto1.TiendaProyecto.Model.Producto;

@Repository
public interface ProductosRepository extends JpaRepository<Producto, Long> {

    @Query(value = "CALL sp_listar_productos()", nativeQuery = true)
    List<Producto> listarTodosProductos();

    @Query(value = "CALL sp_buscar_producto_por_nombre(:p_nombre)", nativeQuery = true)
    Producto buscarNombreProducto(@Param("p_nombre") String nombre);

    @Query(value = "CALL sp_buscar_producto_por_id(:p_id)", nativeQuery = true)
    Producto buscarProductoPorId(@Param("p_id") Long id);

    @Modifying
    @Transactional
    @Query(value = "CALL sp_agregar_producto(:p_nombre, :p_descripcion, :p_precio, :p_stock, :p_categoria_id, :p_proveedor_id)", nativeQuery = true)
    Producto agregarProducto(
        @Param("p_nombre") String nombre,
        @Param("p_descripcion") String descripcion,
        @Param("p_precio") Double precio,
        @Param("p_stock") int stock,
        @Param("p_categoria_id") Long categoriaId,
        @Param("p_proveedor_id") Long proveedorId
    );

    @Modifying
    @Transactional
    @Query(value = "CALL sp_actualizar_producto(:p_id, :p_nombre, :p_descripcion, :p_precio, :p_stock, :p_categoria_id, :p_proveedor_id)", nativeQuery = true)
    Producto actualizarProducto(
        @Param("p_id") Long id,
        @Param("p_nombre") String nombre,
        @Param("p_descripcion") String descripcion,
        @Param("p_precio") Double precio,
        @Param("p_stock") int stock,
        @Param("p_categoria_id") Long categoriaId,
        @Param("p_proveedor_id") Long proveedorId
    );

    @Modifying
    @Transactional
    @Query(value = "CALL sp_eliminar_producto(:p_id)", nativeQuery = true)
    void eliminarProducto(@Param("p_id") Long id);
}
