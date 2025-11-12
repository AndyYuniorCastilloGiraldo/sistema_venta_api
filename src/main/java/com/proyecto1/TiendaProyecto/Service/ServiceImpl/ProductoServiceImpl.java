package com.proyecto1.TiendaProyecto.Service.ServiceImpl;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.TiendaProyecto.Model.Categoria;
import com.proyecto1.TiendaProyecto.Model.Producto;
import com.proyecto1.TiendaProyecto.Repository.CategoriaRepository;
import com.proyecto1.TiendaProyecto.Repository.ProductosRepository;
import com.proyecto1.TiendaProyecto.Service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductosRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepo;

    @Override
    public Producto agregarProducto(Producto producto) {
        if (producto.getCategoria() != null && producto.getCategoria().getCategoriaId() != null) {
            Categoria categoria = categoriaRepo.findById(producto.getCategoria().getCategoriaId())
                    .orElseThrow(() -> new RuntimeException(
                            "Categoría no encontrada con ID: " + producto.getCategoria().getCategoriaId()));
            producto.setCategoria(categoria);
        } else {
            throw new RuntimeException("Debe especificar una categoría válida (con ID).");
        }

        
        return repo.save(producto);
    }

    @Override
    public Producto actualizarProducto(Long id, Producto producto) {
        Optional<Producto> productoExistente = repo.findById(id);

        if (productoExistente.isPresent()) {
            Producto prod = productoExistente.get();
            prod.setNombreProducto(producto.getNombreProducto());
            prod.setPrecio(producto.getPrecio());
            prod.setStock(producto.getStock());
            prod.setDescripcion(producto.getDescripcion());
            prod.setCategoria(producto.getCategoria());

            return repo.save(prod);
        }
        return null;
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Producto obtenerProductoPorNombre(String nombreProducto) {
        return repo.findByNombreProducto(nombreProducto);
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return repo.findAll();
    }

    @Override
    public Boolean eliminarProducto(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
