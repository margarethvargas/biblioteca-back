package com.cibertec.biblioteca.entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fch_salida_libro", columnDefinition = "DATE")
    private String fch_salida_libro;
    @Column(name = "fch_devolucion_estimada", columnDefinition = "DATE")
    private String fch_devolucion_estimada;
    @Column(name = "fch_devolucion_real", columnDefinition = "DATE")
    private String fch_devolucion_real;
    @Column(name = "estado_prestamo", length = 20, columnDefinition = "VARCHAR(20)")
    private String estado_prestamo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;
    @OneToOne
    @JoinColumn(name = "id_reserva", referencedColumnName = "id")
    private Reserva reserva;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFch_salida_libro() {
        return fch_salida_libro;
    }

    public void setFch_salida_libro(String fch_salida_libro) {
        this.fch_salida_libro = fch_salida_libro;
    }

    public String getFch_devolucion_estimada() {
        return fch_devolucion_estimada;
    }

    public void setFch_devolucion_estimada(String fch_devolucion_estimada) {
        this.fch_devolucion_estimada = fch_devolucion_estimada;
    }

    public String getFch_devolucion_real() {
        return fch_devolucion_real;
    }

    public void setFch_devolucion_real(String fch_devolucion_real) {
        this.fch_devolucion_real = fch_devolucion_real;
    }

    public String getEstado_prestamo() {
        return estado_prestamo;
    }

    public void setEstado_prestamo(String estado_prestamo) {
        this.estado_prestamo = estado_prestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
