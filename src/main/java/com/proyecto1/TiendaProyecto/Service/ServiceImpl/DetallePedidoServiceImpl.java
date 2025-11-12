package com.proyecto1.TiendaProyecto.Service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.TiendaProyecto.Model.DetallePedido;
import com.proyecto1.TiendaProyecto.Repository.DetallePedidoRepository;
import com.proyecto1.TiendaProyecto.Service.DetallePedidoService;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository repo;

    @Override
    public DetallePedido listarDetallePedidoPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<DetallePedido> listarTodosLosDetallePedidos() {
        return repo.findAll();
    }
}