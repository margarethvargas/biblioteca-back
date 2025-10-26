package com.cibertec.biblioteca.negocio;

import com.cibertec.biblioteca.entidades.Usuario;
import com.cibertec.biblioteca.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UsuarioNegocio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario grabar(Usuario usuario){
        return usuarioRepositorio.save(usuario);
    }

    public List<Usuario> obtener(){
        return(List<Usuario>) usuarioRepositorio.findAll();
    }

    public Usuario actualizar(Usuario usuario){
        Usuario c = usuarioRepositorio.findById(usuario.getId()).get();
        if(c!=null){
            // Solo actualizar campos no nulos
            if (usuario.getNombre() != null) c.setNombre(usuario.getNombre());
            if (usuario.getApellido() != null) c.setApellido(usuario.getApellido());
            if (usuario.getDni() != null) c.setDni(usuario.getDni());
            if (usuario.getTelefono() != null) c.setTelefono(usuario.getTelefono());
            if (usuario.getEmail() != null) c.setEmail(usuario.getEmail());
            if (usuario.getDireccion() != null) c.setDireccion(usuario.getDireccion());
            if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
                // Encriptar la nueva contraseña
                c.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }
            if (usuario.getRole() != null) c.setRole(usuario.getRole());
            return usuarioRepositorio.save(c);
        }else {return null;}
    }

    public Usuario borrar(Long id){
        Usuario c = usuarioRepositorio.findById(id).get();
        if(c!=null){
            usuarioRepositorio.delete(c);
        }else{return null;}
        return c;
    }

    private List<SimpleGrantedAuthority> getAuthority(Usuario usuario) {
        return Collections.singletonList(new SimpleGrantedAuthority(usuario.getRole()));
    }

    public Usuario findByEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }
}
