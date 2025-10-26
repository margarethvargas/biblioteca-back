package com.cibertec.biblioteca.entidades;

import jakarta.persistence.*;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nombre", length = 20, columnDefinition = "VARCHAR(20)")
    private String nombre;
    @Column(name = "apellido", length = 40, columnDefinition = "VARCHAR(40)")
    private String apellido;
    @Column(name = "dni", length = 8, columnDefinition = "CHAR(8)")
    private String dni;
    @Column(name = "telefono", length = 9, columnDefinition = "CHAR(9)")
    private String telefono;
    @Column(name = "direccion", length = 120, columnDefinition = "VARCHAR(120)")
    private String direccion;
    @Column(name = "email", length = 40, columnDefinition = "VARCHAR(40)", unique = true)
    private String email;
    @Column(name = "password", length = 150, columnDefinition = "VARCHAR(150)")
    private String password;
    @Column(name = "role", length = 20, columnDefinition = "VARCHAR(20)")
    private String role;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
