package com.example.Usuarios12.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.Usuarios12.model.Rol;
import com.example.Usuarios12.model.Usuario;
import com.example.Usuarios12.repository.UsuarioRepository;
import com.example.Usuarios12.service.UsuarioService;
import com.example.Usuarios12.repository.RoleRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearUsuario_debeRetornarUsuarioCreado() {
        String username = "testuser";
        String password = "password";
        Long roleId = 1L;

        Rol rolMock = new Rol(roleId, "Administrador", null);
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(rolMock));
        when(passwordEncoder.encode(password)).thenReturn("encryptedPass");

        Usuario guardado = new Usuario(null, username, "encryptedPass", rolMock);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(guardado);

        Usuario resultado = usuarioService.crearUsuario(username, password, roleId);

        assertNotNull(resultado);
        assertEquals(username, resultado.getUsername());
        assertEquals("encryptedPass", resultado.getPassword());
        assertEquals("Administrador", resultado.getRol().getNombre());
    }
}