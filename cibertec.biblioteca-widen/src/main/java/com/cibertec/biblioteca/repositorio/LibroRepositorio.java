package com.cibertec.biblioteca.repositorio;

import com.cibertec.biblioteca.entidades.Libro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibroRepositorio extends CrudRepository<Libro, Long> {
    @Query("SELECT l FROM Libro l WHERE l.tipoMaterial = :tipoMaterial")
    List<Libro> findByTipoMaterial(String tipoMaterial);
    @Query("SELECT l FROM Libro l WHERE l.titulo LIKE %:titulo%")
    List<Libro> findByTitulo(String titulo);
    @Query("SELECT l FROM Libro l WHERE l.tipoMaterial = :tipoMaterial AND l.titulo LIKE %:titulo%")
    List<Libro> findByTipoMaterialAndTitulo(String tipoMaterial, String titulo);


}
