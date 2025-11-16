package com.example.Usuarios12.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.Usuarios12.model.Rol;
import com.example.Usuarios12.model.Usuario;
import com.example.Usuarios12.repository.RoleRepository;
import com.example.Usuarios12.repository.UsuarioRepository;



@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepo, UsuarioRepository usuarioRepo, BCryptPasswordEncoder encoder) {
        return args -> {
            if (roleRepo.count() == 0 && usuarioRepo.count() == 0) {
                Rol admin = new Rol();
                admin.setNombre("Administrador");
                roleRepo.save(admin);

                Rol cliente = new Rol();
                cliente.setNombre("Cliente");
                roleRepo.save(cliente);

                Rol tecnico = new Rol();
                tecnico.setNombre("Tecnico");
                roleRepo.save(tecnico);

                // 🔐 Contraseñas encriptadas
                usuarioRepo.save(new Usuario(null, "victor", encoder.encode("12345"), admin));
                usuarioRepo.save(new Usuario(null, "Joselito", encoder.encode("12345"), cliente));
                usuarioRepo.save(new Usuario(null, "Pacherco", encoder.encode("12345"), tecnico));

                System.out.println("Usuarios creados con contraseña encriptada");
            } else {
                System.out.println("ℹDatos ya existen. No se cargaron nuevos datos.");
            }
        };
    }
}