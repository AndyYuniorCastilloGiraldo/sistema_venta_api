package com.proyecto1.TiendaProyecto.Service;

import java.util.List;

import com.proyecto1.TiendaProyecto.Model.Cliente;

public interface ClienteService {
    List<Cliente> listar();
    Cliente registrar(Cliente cliente);
    Cliente actualizar(long idCliente, Cliente cliente);
    boolean eliminar(Long idCliente);
    List<Cliente> buscarId(Long idCliente);
}
