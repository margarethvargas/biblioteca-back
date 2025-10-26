package com.cibertec.biblioteca.entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="libro")
public class Libro {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipoMaterial() { return tipoMaterial; }

    public void setTipoMaterial(String tipo_material) {
        this.tipoMaterial = tipo_material;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAno_public() {
        return ano_public;
    }

    public void setAno_public(String ano_public) {
        this.ano_public = ano_public;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getCant_dispon() {
        return cant_dispon;
    }

    public void setCant_dispon(Integer cant_dispon) {
        this.cant_dispon = cant_dispon;
    }

    public String getEstado_libro() {
        return estado_libro;
    }

    public void setEstado_libro(String estado_libro) {
        this.estado_libro = estado_libro;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "tipo_material", length = 50, columnDefinition = "VARCHAR(50)")
    private String tipoMaterial;
    @Column(name = "titulo", length = 50, columnDefinition = "VARCHAR(50)")
    private String titulo;
    @Column(name = "autor", length = 50, columnDefinition = "VARCHAR(50)")
    private String autor;
    @Column(name = "editorial", length = 50, columnDefinition = "VARCHAR(50)")
    private String editorial;
    @Column(name = "ano_public", length = 4, columnDefinition = "CHAR(4)")
    private String ano_public;
    @Column(name = "genero", length = 20, columnDefinition = "VARCHAR(20)")
    private String genero;
    @Column(name = "isbn", length = 13, columnDefinition = "CHAR(13)")
    private String isbn;
    private Integer cant_dispon;
    @Column(name = "estado_libro", length = 20, columnDefinition = "VARCHAR(20)")
    private String estado_libro;
    @Column(name = "resumen", columnDefinition = "TEXT")
    private String resumen;
    @Column(name = "categoria", length = 20, columnDefinition = "VARCHAR(200)")
    private String categoria;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas;

}
