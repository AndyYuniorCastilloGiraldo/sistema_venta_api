package com.proyecto1.TiendaProyecto.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto1.TiendaProyecto.Model.Cliente;
import com.proyecto1.TiendaProyecto.Service.ClienteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = service.guardarCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = service.buscarClientePorId(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            String mensaje = "No hay cliente con el ID " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        Cliente clienteExistente = service.buscarClientePorId(id);
        if (clienteExistente != null) {
            clienteExistente.setNombreCliente(clienteActualizado.getNombreCliente());
            clienteExistente.setApellidoCliente(clienteActualizado.getApellidoCliente());
            clienteExistente.setEmail(clienteActualizado.getEmail());
            clienteExistente.setTelefono(clienteActualizado.getTelefono());
            clienteExistente.setDireccion(clienteActualizado.getDireccion());

            Cliente clienteGuardado = service.guardarCliente(clienteExistente);
            return ResponseEntity.ok(clienteGuardado);
        } else {
            String mensaje = "No hay cliente con el ID " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> obtenerTodosLosClientes() {
        List<Cliente> clientes = service.buscarTodosLosClientes();
        return ResponseEntity.ok(clientes);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
        boolean eliminado = service.eliminarCliente(id);
        if (eliminado) {
            String mensaje = "Cliente con ID " + id + " eliminado exitosamente.";
            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No hay cliente con el ID " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }
}
