package com.proyecto1.TiendaProyecto.Service;

import java.util.List;
import com.proyecto1.TiendaProyecto.Model.Pedido;

public interface PedidoService {

    Pedido crearPedido(Pedido pedido);
    Pedido obtenerPedidoPorId(Long id);
    List<Pedido> obtenerTodosLosPedidos();
    Pedido actualizarPedido(Long id, Pedido pedidoActualizado);
    boolean eliminarPedido(Long id);
}
