 package com.proyecto1.TiendaProyecto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto1.TiendaProyecto.Model.DetallePedido;
import com.proyecto1.TiendaProyecto.Model.Pedido;
  
 @Repository
 public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    List<DetallePedido> findByPedido(Pedido pedido);
    
} 
