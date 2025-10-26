package com.cibertec.biblioteca.repositorio;

import com.cibertec.biblioteca.entidades.Prestamo;
import org.springframework.data.repository.CrudRepository;

public interface PrestamoRepositorio extends CrudRepository<Prestamo, Long> {
}
