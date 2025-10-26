package com.cibertec.biblioteca.rest;

import com.cibertec.biblioteca.entidades.Libro;
import com.cibertec.biblioteca.negocio.LibroNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/libro")
public class libroREST {
    @Autowired
    private LibroNegocio libroNegocio;

    @PostMapping("")
    public Libro grabar(@RequestBody Libro libro) {
        return libroNegocio.grabar(libro);
    }

    @GetMapping("")
    public List<Libro> obtener(@RequestParam(value = "tipo_material", required = false) String tipo_material,
                               @RequestParam(value = "nombre", required = false) String nombre) {
        try {
            return libroNegocio.obtenerPorTipoYNombre(tipo_material, nombre);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible obtener los datos");
        }
    }

    @GetMapping("/{id}")
    public Libro obtenerPorId(@PathVariable("id") Long id) {
        try {
            return libroNegocio.obtenerPorId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado");
        }
    }

    @PutMapping("")
    public Libro actualizar(@RequestBody Libro libro) {
        try{
            return libroNegocio.actualizar(libro);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible actualizar los datos");
        }
    }

    @PutMapping("/{id}")
    public Libro actualizarPorId(@PathVariable("id") Long id, @RequestBody Libro libro) {
        try {
            libro.setId(id);
            return libroNegocio.actualizar(libro);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible actualizar el libro");
        }
    }

    @DeleteMapping("/{id}")
    public Libro borrar(@PathVariable(value = "id") Long id) {
        return libroNegocio.borrar(id);
    }
}
