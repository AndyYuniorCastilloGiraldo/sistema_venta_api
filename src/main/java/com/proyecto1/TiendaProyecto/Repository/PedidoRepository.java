package com.proyecto1.TiendaProyecto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto1.TiendaProyecto.Model.Pedido;
import com.proyecto1.TiendaProyecto.Model.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuario(Usuario usuario);
    
}
