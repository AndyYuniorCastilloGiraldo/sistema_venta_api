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
    public Cliente guardarCliente(Cliente cliente) {
        return repo.save(cliente);
    }

    @Override
    public Cliente buscarClientePorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> buscarTodosLosClientes() {
        return repo.findAll();
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return repo.save(cliente);
    }

    @Override
    public boolean eliminarCliente(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
    
}
