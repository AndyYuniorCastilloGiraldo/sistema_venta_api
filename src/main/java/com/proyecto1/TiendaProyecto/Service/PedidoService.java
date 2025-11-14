package com.proyecto1.TiendaProyecto.Service;

import java.util.List;
import com.proyecto1.TiendaProyecto.Model.Pedido;

public interface PedidoService {

    Pedido crearPedido(Pedido pedido, String username); 
    Pedido buscarPedidoPorId(Long id);  
    List<Pedido> obtenerTodosLosPedidos();
    Pedido actualizarEstado(Long id, String nuevoEstado);
    void eliminarPedido(Long id);  // ← Cambia a void
    List<Pedido> obtenerPedidosPorUsuario(String username);  
}
