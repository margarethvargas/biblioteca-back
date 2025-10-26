package com.cibertec.biblioteca.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "fch_solicitud", columnDefinition = "DATE")
    private String fch_solicitud;
    @Column(name = "fch_inicio", columnDefinition = "DATE")
    private String fch_inicio;
    @Column(name = "fch_fin", columnDefinition = "DATE")
    private String fch_fin;
    @Column(name = "fch_recojo", columnDefinition = "DATE")
    private String fch_recojo;
    @Column(name = "estado_reserva", length = 20, columnDefinition = "VARCHAR(20)")
    private String estado_reserva;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_libro", referencedColumnName = "id")
    private Libro libro;

    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    @JsonIgnore
    private Prestamo prestamo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFch_solicitud() {
        return fch_solicitud;
    }

    public void setFch_solicitud(String fch_solicitud) {
        this.fch_solicitud = fch_solicitud;
    }

    public String getFch_inicio() {
        return fch_inicio;
    }

    public void setFch_inicio(String fch_inicio) {
        this.fch_inicio = fch_inicio;
    }

    public String getFch_fin() {
        return fch_fin;
    }

    public void setFch_fin(String fch_fin) {
        this.fch_fin = fch_fin;
    }

    public String getFch_recojo() {
        return fch_recojo;
    }

    public void setFch_recojo(String fch_recojo) {
        this.fch_recojo = fch_recojo;
    }

    public String getEstado_reserva() {
        return estado_reserva;
    }

    public void setEstado_reserva(String estado_reserva) {
        this.estado_reserva = estado_reserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
}
