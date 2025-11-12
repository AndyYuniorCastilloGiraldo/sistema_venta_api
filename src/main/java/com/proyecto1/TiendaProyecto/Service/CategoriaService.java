package com.proyecto1.TiendaProyecto.Service;

import java.util.List;
import com.proyecto1.TiendaProyecto.Model.Categoria;

public interface CategoriaService {
    Categoria agregarCategoria(Categoria categoria);
    Categoria actualizarCategoria(Long id, Categoria categoria);
    Categoria obtenerCategoriaPorId(Long id);
    Categoria obtenerCategoriaPorNombre(String nombre);
    List<Categoria> obtenerTodasLasCategorias();
    Boolean eliminarCategoria(Long id);
}
