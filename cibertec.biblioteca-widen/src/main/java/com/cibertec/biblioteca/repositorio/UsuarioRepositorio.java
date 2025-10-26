package com.cibertec.biblioteca.repositorio;

import com.cibertec.biblioteca.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
