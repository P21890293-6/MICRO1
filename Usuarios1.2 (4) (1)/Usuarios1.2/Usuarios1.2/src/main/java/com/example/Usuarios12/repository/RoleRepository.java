package com.example.Usuarios12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Usuarios12.model.Rol;

@Repository
public interface RoleRepository extends JpaRepository<Rol, Long> {

}

