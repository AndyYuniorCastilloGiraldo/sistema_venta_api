package com.proyecto1.TiendaProyecto.Service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.TiendaProyecto.Model.DetallePedido;
import com.proyecto1.TiendaProyecto.Model.Pedido;
import com.proyecto1.TiendaProyecto.Repository.DetallePedidoRepository;
import com.proyecto1.TiendaProyecto.Repository.PedidoRepository;
import com.proyecto1.TiendaProyecto.Service.DetallePedidoService;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository repo;
    @Autowired
    private PedidoRepository pedidoRepo;
@Override
    public DetallePedido listarDetallePedidoPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado"));
    }

    @Override
    public List<DetallePedido> listarTodosLosDetallePedidos() {
        return repo.findAll();
    }
    
    // ← AGREGAR este método
    @Override
    public List<DetallePedido> listarDetallesPorPedido(Long pedidoId) {
       Pedido pedido = pedidoRepo.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        
        return repo.findByPedido(pedido);
    }
}