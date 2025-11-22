package com.proyecto1.TiendaProyecto.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto1.TiendaProyecto.Model.Producto;
import com.proyecto1.TiendaProyecto.Service.ProductoService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/productos")
public class ProductoController {

@Autowired
   private ProductoService service;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = service.agregarProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = service.obtenerProductoPorId(id);
        if (producto != null) return ResponseEntity.ok(producto);
        return ResponseEntity.status(404).body("Producto no encontrado con ID " + id);
    }

    @GetMapping("/nombre/{nombre}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> obtenerProductoPorNombre(@PathVariable String nombre) {
        Producto producto = service.obtenerProductoPorNombre(nombre);
        if (producto != null) return ResponseEntity.ok(producto);
        return ResponseEntity.status(404).body("Producto no encontrado con nombre " + nombre);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        return ResponseEntity.ok(service.obtenerTodosLosProductos());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        Producto producto = service.actualizarProducto(id, productoActualizado);
        if (producto != null) return ResponseEntity.ok(producto);
        return ResponseEntity.status(404).body("Producto no encontrado con ID " + id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        boolean eliminado = service.eliminarProducto(id);
        if (eliminado) return ResponseEntity.ok("Producto con ID " + id + " eliminado exitosamente.");
        return ResponseEntity.status(404).body("Producto no encontrado con ID " + id);
    }

}
