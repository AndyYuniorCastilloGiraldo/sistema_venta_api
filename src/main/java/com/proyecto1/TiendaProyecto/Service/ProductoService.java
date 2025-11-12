package com.proyecto1.TiendaProyecto.Service;

import java.util.List;

import com.proyecto1.TiendaProyecto.Model.Producto;

public interface ProductoService{
    Producto agregarProducto(Producto producto);
    Producto actualizarProducto(Long id, Producto producto);
    Producto obtenerProductoPorId(Long id);
    Producto obtenerProductoPorNombre(String nombreProducto);
    List<Producto> obtenerTodosLosProductos();
    Boolean eliminarProducto(Long id);
}
