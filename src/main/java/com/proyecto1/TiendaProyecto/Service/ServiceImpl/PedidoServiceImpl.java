package com.proyecto1.TiendaProyecto.Service.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.TiendaProyecto.Model.Cliente;
import com.proyecto1.TiendaProyecto.Model.DetallePedido;
import com.proyecto1.TiendaProyecto.Model.Pedido;
import com.proyecto1.TiendaProyecto.Model.Producto;
import com.proyecto1.TiendaProyecto.Repository.ClienteRepository;
import com.proyecto1.TiendaProyecto.Repository.PedidoRepository;
import com.proyecto1.TiendaProyecto.Repository.ProductosRepository;
import com.proyecto1.TiendaProyecto.Service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository repo;
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private ProductosRepository productoRepo;

    @Override
public Pedido crearPedido(Pedido pedido) {
    Cliente cliente = clienteRepo.findById(pedido.getCliente().getClienteId())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    pedido.setCliente(cliente);

    double total = 0;
    List<DetallePedido> detallesFinales = new ArrayList<>();

    for (DetallePedido detalle : pedido.getDetalles()) {
        Producto producto = productoRepo.findById(detalle.getProducto().getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        double subtotal = producto.getPrecio() * detalle.getCantidad();
        total += subtotal;

        detalle.setProducto(producto);
        detalle.setPedido(pedido);
        detalle.setPrecioUnitario(producto.getPrecio());
        detalle.setSubtotal(subtotal);

        detallesFinales.add(detalle);
    }

    pedido.setDetalles(detallesFinales);
    pedido.setTotal(total);
    pedido.setEstado("PENDIENTE");

    return repo.save(pedido);
}



    @Override
    public Pedido obtenerPedidoPorId(Long id) {
        return repo.findById(id).orElse(null);
        
    }

    @Override
    public List<Pedido> obtenerTodosLosPedidos() {
        return repo.findAll();
    }

    @Override
    public Pedido actualizarPedido(Long id, Pedido pedidoActualizado) {
        Pedido pedidoExistente = repo.findById(id).orElse(null);
        if (pedidoExistente != null) {
            pedidoExistente.setTotal(pedidoActualizado.getTotal());
            pedidoExistente.setEstado(pedidoActualizado.getEstado());
            return repo.save(pedidoExistente);
        }
        return null;
    }

    @Override
    public boolean eliminarPedido(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
    
}
