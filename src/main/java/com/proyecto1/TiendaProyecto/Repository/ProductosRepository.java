package com.proyecto1.TiendaProyecto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto1.TiendaProyecto.Model.Producto;

@Repository
public interface ProductosRepository extends JpaRepository<Producto, Long> {

    Producto findByNombreProducto(String nombreProducto);
}
