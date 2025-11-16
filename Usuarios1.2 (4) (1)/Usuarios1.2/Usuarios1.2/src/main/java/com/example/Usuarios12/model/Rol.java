package com.example.Usuarios12.model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //se reconozca com una entidad JPA
@Table(name = "roles") //nombre d ela tabla para la BD
@Data //metodos setters, getters, tostring
@AllArgsConstructor //constructir con todos los datos
@NoArgsConstructor //constructor vacio
public class Rol {
    @Id //identificador primario
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrementable
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    //identificamos a relacion inversa: un rol puede ser asigando a muchos usuarios
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    @JsonIgnore //no se incluiran los usuarios cuando se consulte los roles
    private List<Usuario> users;
}