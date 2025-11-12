package com.proyecto1.TiendaProyecto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.proyecto1.TiendaProyecto.Model.Pedido;
import com.proyecto1.TiendaProyecto.Service.PedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = service.crearPedido(pedido);
        return ResponseEntity.ok(nuevoPedido);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedidoActualizado) {
        Pedido pedidoExistente = service.obtenerPedidoPorId(id);
        if (pedidoExistente != null) {
            pedidoExistente.setTotal(pedidoActualizado.getTotal());
            pedidoExistente.setEstado(pedidoActualizado.getEstado());

            Pedido pedidoGuardado = service.actualizarPedido(id, pedidoExistente);
            return ResponseEntity.ok("Pedido actualizado: " + pedidoGuardado);
        } else {
            String mensaje = "No hay pedido con el ID " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPedidoPorId(@PathVariable Long id) {
        Pedido pedido = service.obtenerPedidoPorId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            String mensaje = "No hay pedido con el ID " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerPedidos(@RequestParam(required = false) Long id) {
        return ResponseEntity.ok(service.obtenerTodosLosPedidos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Long id) {
        boolean eliminado = service.eliminarPedido(id);
        if (eliminado) {
            return ResponseEntity.ok("Pedido con ID " + id + " eliminado correctamente.");
        } else {
            String mensaje = "No hay pedido con el ID " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }
    
}
