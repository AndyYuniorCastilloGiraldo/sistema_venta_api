package com.proyecto1.TiendaProyecto.Service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.TiendaProyecto.Model.Cliente;
import com.proyecto1.TiendaProyecto.Repository.ClienteRepository;
import com.proyecto1.TiendaProyecto.Service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Override
    public List<Cliente> listar() {
        return repo.listarClientes();
    }

    @Override
    public Cliente registrar(Cliente cliente) {
        return repo.registrarCliente(
            cliente.getNombreCliente(),
            cliente.getApellidoCliente(),
            cliente.getEmail(),
            cliente.getTelefono(),
            cliente.getDireccion()
        );
    }

    @Override
    public Cliente actualizar(long idCliente, Cliente cliente) {
        return repo.actualizarCliente(
            idCliente,
            cliente.getNombreCliente(),
            cliente.getApellidoCliente(),
            cliente.getEmail(),
            cliente.getTelefono(),
            cliente.getDireccion()
        );
    }

    @Override
    public boolean eliminar(Long idCliente) {
        try {
            repo.eliminarCliente(idCliente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Cliente> buscarId(Long idCliente) {
        Cliente cliente = repo.buscarPorId(idCliente);
        return cliente != null ? List.of(cliente) : List.of();
    }

    
}
