package com.proyecto1.TiendaProyecto.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto1.TiendaProyecto.Model.DetallePedido;
import com.proyecto1.TiendaProyecto.Service.DetallePedidoService;

@RestController
@RequestMapping("/api/detallepedidos")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService service;

    //mirar si el pedido es de el usuario o es admin
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> obtenerDetallePedidoPorId(
            @PathVariable Long id, 
            Authentication auth) {
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        DetallePedido detalle = service.listarDetallePedidoPorId(id);

        if (!isAdmin && !detalle.getPedido().getUsuario().getUsername().equals(username)) {
            return ResponseEntity.status(403)
                .body("No tienes permiso para ver este detalle");
        }
        
        return ResponseEntity.ok(detalle);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DetallePedido>> listarTodosLosDetallePedidos() {
        List<DetallePedido> detalles = service.listarTodosLosDetallePedidos();
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/pedido/{pedidoId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> obtenerDetallesPorPedido(
            @PathVariable Long pedidoId,
            Authentication auth) {
        
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        List<DetallePedido> detalles = service.listarDetallesPorPedido(pedidoId);
        
        if (!detalles.isEmpty()) {
            // Verificar que el pedido pertenece al usuario
            if (!isAdmin && !detalles.get(0).getPedido().getUsuario().getUsername().equals(username)) {
                return ResponseEntity.status(403)
                    .body("No tienes permiso para ver estos detalles");
            }
        }
        
        return ResponseEntity.ok(detalles);
    }
}