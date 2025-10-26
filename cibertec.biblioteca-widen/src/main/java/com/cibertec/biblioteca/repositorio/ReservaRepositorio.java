package com.cibertec.biblioteca.repositorio;

import com.cibertec.biblioteca.entidades.Reserva;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservaRepositorio extends CrudRepository<Reserva, Long> {
    List<Reserva> findByUsuarioId(Long idUsuario);
}
