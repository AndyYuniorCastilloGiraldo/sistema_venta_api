package com.proyecto1.TiendaProyecto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
   public ResponseEntity<?> agregarProducto(@RequestBody Producto producto) {
       Producto nuevoProducto = service.agregarProducto(producto);
       return ResponseEntity.ok(nuevoProducto);
   }

   @GetMapping("/{id}")
   public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {
       Producto producto = service.obtenerProductoPorId(id);
       if (producto != null) {
           return ResponseEntity.ok(producto);
       } else {
           String mensaje = "No hay producto con el ID " + id;
           return ResponseEntity.status(404).body(mensaje);
       }
   }

   @GetMapping("/nombre")
   public ResponseEntity<?> obtenerProductoPorNombre(@RequestParam String nombre) {
       Producto producto = service.obtenerProductoPorNombre(nombre);
       if (producto != null) {
           return ResponseEntity.ok(producto);
       } else {
           String mensaje = "No hay producto con el nombre " + nombre;
           return ResponseEntity.status(404).body(mensaje);
       }
   }
   
   @GetMapping
   public ResponseEntity<?> obtenerProductos(@RequestParam(required = false) String nombre) {
       return ResponseEntity.ok(service.obtenerTodosLosProductos());
   }
   
   @PutMapping("/{id}")
   public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
       Producto productoActualizado = service.actualizarProducto(id, producto);
       if (productoActualizado != null) {
           return ResponseEntity.ok(productoActualizado);
       } else {
           String mensaje = "No se pudo actualizar el producto con ID " + id;
           return ResponseEntity.status(404).body(mensaje);
       }
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
       Boolean eliminado = service.eliminarProducto(id);
       if (eliminado) {
           String mensaje = "Producto con ID " + id + " eliminado correctamente.";
           return ResponseEntity.ok(mensaje);
       } else {
           String mensaje = "No hay producto con el ID " + id;
           return ResponseEntity.status(404).body(mensaje);
       }
   }

}
