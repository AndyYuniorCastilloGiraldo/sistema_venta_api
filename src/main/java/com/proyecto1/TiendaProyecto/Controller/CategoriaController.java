package com.proyecto1.TiendaProyecto.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto1.TiendaProyecto.Model.Categoria;
import com.proyecto1.TiendaProyecto.Service.CategoriaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaService service;

    @PostMapping
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = service.agregarCategoria(categoria);
        return ResponseEntity.ok(nuevaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaActualizada) {
        Categoria categoriaExistente = service.obtenerCategoriaPorId(id);
        if (categoriaExistente != null) {
            categoriaExistente.setNombreCategoria(categoriaActualizada.getNombreCategoria());

            Categoria categoriaGuardada = service.actualizarCategoria(id, categoriaExistente);
            return ResponseEntity.ok(categoriaGuardada);
        } else {
            String mensaje = "No hay categoría con el ID " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }
    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerTodasLasCategorias() {
        List<Categoria> categorias = service.obtenerTodasLasCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = service.obtenerCategoriaPorId(id);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            String mensaje = "No hay categoría con el ID " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @GetMapping("/nombre")
    public ResponseEntity<?> obtenerCategoriaPorNombre(@RequestParam String nombre) {
        Categoria categoria = service.obtenerCategoriaPorNombre(nombre);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            String mensaje = "No hay categoría con el nombre " + nombre;
            return ResponseEntity.status(404).body(mensaje);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id) {
        Boolean eliminado = service.eliminarCategoria(id);
        if (eliminado) {
            String mensaje = "Categoría con ID " + id + " eliminada correctamente.";
            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No hay categoría con el ID " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }
    

}
