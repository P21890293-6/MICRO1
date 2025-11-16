package com.example.Usuarios12.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import com.example.Usuarios12.controller.UsuarioController;
import com.example.Usuarios12.model.Rol;
import com.example.Usuarios12.model.Usuario;
import com.example.Usuarios12.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;



@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)  // Esto desactiva la seguridad para la prueba
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    void getUsuarios_deberiaRetornarListaUsuariosYStatus200() throws Exception {
        // 1. Preparar datos de prueba
        Rol rol = new Rol(1L, "Cliente", null);
        Usuario usuario = new Usuario(1L, "usuario1", "pass123", rol);
        List<Usuario> listaUsuarios = List.of(usuario);

        // 2. Configurar el mock
        when(usuarioService.obtenerUsuarios()).thenReturn(listaUsuarios);

        // 3. Hacer la petición y verificar
        mockMvc.perform(get("/api/v1/usuario/users")  // ¡OJO! La ruta es con minúsculas
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].username").value("usuario1"))
               .andExpect(jsonPath("$[0].rol.nombre").value("Cliente"));
    }
}

