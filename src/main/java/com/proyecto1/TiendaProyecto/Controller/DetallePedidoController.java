package com.proyecto1.TiendaProyecto.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public DetallePedido obtenerDetallePedidoPorId(@PathVariable Long id) {
        return service.listarDetallePedidoPorId(id);
    }

    @GetMapping
    public List<DetallePedido> listarTodosLosDetallePedidos() {
        return service.listarTodosLosDetallePedidos();
    }
}
