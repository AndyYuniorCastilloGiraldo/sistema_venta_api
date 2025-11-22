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
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(service.registrar(cliente));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        List<Cliente> clientes = service.buscarId(id);
        return clientes.isEmpty() ? 
            ResponseEntity.notFound().build() : 
            ResponseEntity.ok(clientes.get(0));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        List<Cliente> existentes = service.buscarId(id);
        if (existentes.isEmpty()) return ResponseEntity.notFound().build();

        Cliente c = existentes.get(0);
        c.setNombreCliente(cliente.getNombreCliente());
        c.setApellidoCliente(cliente.getApellidoCliente());
        c.setEmail(cliente.getEmail());
        c.setTelefono(cliente.getTelefono());
        c.setDireccion(cliente.getDireccion());

        return ResponseEntity.ok(service.actualizar(id, c));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Cliente>> obtenerTodos() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        return service.eliminar(id) ? 
            ResponseEntity.ok("Cliente con ID " + id + " eliminado exitosamente.") :
            ResponseEntity.status(404).body("No hay cliente con el ID " + id);
    }
}
