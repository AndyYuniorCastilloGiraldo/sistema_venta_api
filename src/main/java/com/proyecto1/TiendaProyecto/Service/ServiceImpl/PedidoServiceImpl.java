package com.proyecto1.TiendaProyecto.Service.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto1.TiendaProyecto.Model.Cliente;
import com.proyecto1.TiendaProyecto.Model.DetallePedido;
import com.proyecto1.TiendaProyecto.Model.Pedido;
import com.proyecto1.TiendaProyecto.Model.Producto;
import com.proyecto1.TiendaProyecto.Model.Usuario;
import com.proyecto1.TiendaProyecto.Repository.ClienteRepository;
import com.proyecto1.TiendaProyecto.Repository.PedidoRepository;
import com.proyecto1.TiendaProyecto.Repository.ProductosRepository;
import com.proyecto1.TiendaProyecto.Repository.UsuarioRepository;
import com.proyecto1.TiendaProyecto.Service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository repo;
    
    @Autowired
    private ClienteRepository clienteRepo;
    
    @Autowired
    private ProductosRepository productoRepo;
    
    @Autowired
    private UsuarioRepository usuarioRepo;  

    @Override
    @Transactional
    public Pedido crearPedido(Pedido pedido, String username) {
        Usuario usuario = usuarioRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        pedido.setUsuario(usuario);  
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
    public Pedido buscarPedidoPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }

    @Override
    public List<Pedido> obtenerTodosLosPedidos() {
        return repo.findAll();
    }

    @Override
    @Transactional
    public Pedido actualizarEstado(Long id, String nuevoEstado) {
        Pedido pedido = buscarPedidoPorId(id);
        pedido.setEstado(nuevoEstado);
        return repo.save(pedido);
    }

    @Override
    @Transactional
    public void eliminarPedido(Long id) {
        Pedido pedido = buscarPedidoPorId(id);
        repo.delete(pedido);
    }

    @Override
    public List<Pedido> obtenerPedidosPorUsuario(String username) {
        Usuario usuario = usuarioRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        return repo.findByUsuario(usuario); 
    }
}