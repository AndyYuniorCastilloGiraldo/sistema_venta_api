package com.proyecto1.TiendaProyecto.Service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.TiendaProyecto.Model.Producto;
import com.proyecto1.TiendaProyecto.Repository.ProductosRepository;
import com.proyecto1.TiendaProyecto.Service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductosRepository repo;

    @Override
    public Producto agregarProducto(Producto producto) {
        return repo.agregarProducto(
            producto.getNombreProducto(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getStock(),
            producto.getCategoria().getCategoriaId(),
            producto.getProveedor().getProveedorId()
        );
    }

    @Override
    public Producto actualizarProducto(Long id, Producto producto) {
        return repo.actualizarProducto(
            id,
            producto.getNombreProducto(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getStock(),
            producto.getCategoria().getCategoriaId(),
            producto.getProveedor().getProveedorId()
        );
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return repo.buscarProductoPorId(id);
    }

    @Override
    public Producto obtenerProductoPorNombre(String nombreProducto) {
        return repo.buscarNombreProducto(nombreProducto);
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return repo.listarTodosProductos();
    }

    @Override
    public Boolean eliminarProducto(Long id) {
        repo.eliminarProducto(id);
        return true; 
    }
}
