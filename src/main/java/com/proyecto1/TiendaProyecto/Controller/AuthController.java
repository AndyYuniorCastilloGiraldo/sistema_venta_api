package com.proyecto1.TiendaProyecto.Controller;

import com.proyecto1.TiendaProyecto.Model.AuthRequest;
import com.proyecto1.TiendaProyecto.Model.AuthResponse;
import com.proyecto1.TiendaProyecto.Model.Role;
import com.proyecto1.TiendaProyecto.Model.Usuario;
import com.proyecto1.TiendaProyecto.Repository.RoleRepository;
import com.proyecto1.TiendaProyecto.Repository.UsuarioRepository;
import com.proyecto1.TiendaProyecto.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            Usuario usuario = usuarioRepository.findByUsername(authRequest.getUsername())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado"));
            String token = jwtTokenProvider.generarToken(usuario);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody AuthRequest authRequest) {
        if (usuarioRepository.findByUsername(authRequest.getUsername()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "El usuario ya existe"));
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(authRequest.getUsername());
        nuevoUsuario.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        Role rolUser = roleRepository.findByNombre("USER")
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Rol USER no encontrado"));

        nuevoUsuario.setRoles(Collections.singleton(rolUser));
        usuarioRepository.save(nuevoUsuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message", "Usuario registrado con éxito"));
    }
}
