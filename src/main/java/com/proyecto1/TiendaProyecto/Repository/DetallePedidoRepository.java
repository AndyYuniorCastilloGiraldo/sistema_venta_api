 package com.proyecto1.TiendaProyecto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto1.TiendaProyecto.Model.DetallePedido;
  
 @Repository
 public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    
} 
