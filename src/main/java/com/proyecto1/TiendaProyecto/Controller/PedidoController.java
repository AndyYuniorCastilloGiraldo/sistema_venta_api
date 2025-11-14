package com.proyecto1.TiendaProyecto.Controller;

import com.proyecto1.TiendaProyecto.Model.Pedido;
import com.proyecto1.TiendaProyecto.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> crearPedido(@RequestBody Pedido pedido, Authentication auth) {
        String username = auth.getName();
        Pedido nuevoPedido = service.crearPedido(pedido, username);
        return ResponseEntity.ok(nuevoPedido);
    }

    @GetMapping("/mis-pedidos")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> obtenerMisPedidos(Authentication auth) {
        String username = auth.getName();
        List<Pedido> pedidos = service.obtenerPedidosPorUsuario(username);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> obtenerPedidoPorId(@PathVariable Long id, Authentication auth) {
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        Pedido pedido = service.buscarPedidoPorId(id);
        
        if (!isAdmin && !pedido.getUsuario().getUsername().equals(username)) {
            return ResponseEntity.status(403)
                .body("No tienes permiso para ver este pedido");
        }
        
        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> obtenerTodosLosPedidos() {
        List<Pedido> pedidos = service.obtenerTodosLosPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> actualizarEstadoPedido(
            @PathVariable Long id, 
            @RequestBody Map<String, String> request) {
        String nuevoEstado = request.get("estado");
        Pedido pedido = service.actualizarEstado(id, nuevoEstado);
        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminarPedido(@PathVariable Long id) {
        service.eliminarPedido(id);
        return ResponseEntity.ok("Pedido eliminado exitosamente");
    }
}