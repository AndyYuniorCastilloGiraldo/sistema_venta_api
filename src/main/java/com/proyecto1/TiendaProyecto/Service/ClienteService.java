package com.proyecto1.TiendaProyecto.Service;

import java.util.List;

import com.proyecto1.TiendaProyecto.Model.Cliente;

public interface ClienteService {
    Cliente guardarCliente(Cliente cliente);
    Cliente buscarClientePorId(Long id);
    List<Cliente> buscarTodosLosClientes();
    Cliente actualizarCliente(Cliente cliente);
    boolean eliminarCliente(Long id);
}
