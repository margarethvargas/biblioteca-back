package com.cibertec.biblioteca.negocio;

import com.cibertec.biblioteca.entidades.Libro;
import com.cibertec.biblioteca.repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroNegocio {
    @Autowired
    private LibroRepositorio libroRepositorio;

    public Libro grabar(Libro libro){
        return libroRepositorio.save(libro);
    }

    public List<Libro> obtener(){
        return(List<Libro>) libroRepositorio.findAll();
    }
    public Libro obtenerPorId(Long id) {
        Optional<Libro> libro = libroRepositorio.findById(id);
        if (libro.isPresent()) {
            return libro.get();
        } else {
            throw new RuntimeException("Libro no encontrado");
        }
    }
    public List<Libro> obtenerPorTipoYNombre(String tipo_material, String nombre) {
        if (tipo_material != null && nombre != null) {
            return libroRepositorio.findByTipoMaterialAndTitulo(tipo_material, nombre);
        } else if (tipo_material != null) {
            return libroRepositorio.findByTipoMaterial(tipo_material);
        } else if (nombre != null) {
            return libroRepositorio.findByTitulo(nombre);
        } else {
            return obtener(); // Retorna todos los libros si no se especifican filtros
        }
    }

    public Libro actualizar(Libro libro){
        Libro c = libroRepositorio.findById(libro.getId()).get();
        if(c!=null){
            return libroRepositorio.save(libro);
        }else {return null;}
    }

    public Libro borrar(Long id){
        Libro c = libroRepositorio.findById(id).get();
        if(c!=null){
            libroRepositorio.delete(c);
        }else{return null;}
        return c;
    }
}
