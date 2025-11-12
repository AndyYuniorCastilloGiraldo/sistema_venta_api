package com.proyecto1.TiendaProyecto.Service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.TiendaProyecto.Model.Categoria;
import com.proyecto1.TiendaProyecto.Repository.CategoriaRepository;
import com.proyecto1.TiendaProyecto.Service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    @Override
    public Categoria agregarCategoria(Categoria categoria) {
       return repo.save(categoria);
    }

    @Override
    public Categoria actualizarCategoria(Long id, Categoria categoria) {
        Categoria categoriaExistente = repo.findById(id).orElse(null);
        if (categoriaExistente != null) {
            categoriaExistente.setNombreCategoria(categoria.getNombreCategoria());
            return repo.save(categoriaExistente);
        }
        return null;
    }

    @Override
    public Categoria obtenerCategoriaPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Categoria obtenerCategoriaPorNombre(String nombre) {
        return repo.findByNombreCategoria(nombre);
    }

    @Override
    public List<Categoria> obtenerTodasLasCategorias() {
        return repo.findAll();
        
    }

    @Override
    public Boolean eliminarCategoria(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
    
}
