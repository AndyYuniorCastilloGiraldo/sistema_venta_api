package com.proyecto1.TiendaProyecto.Service;

import java.util.List;
import com.proyecto1.TiendaProyecto.Model.DetallePedido;

public interface DetallePedidoService {
    DetallePedido listarDetallePedidoPorId(Long id);
    List<DetallePedido> listarTodosLosDetallePedidos();
}
