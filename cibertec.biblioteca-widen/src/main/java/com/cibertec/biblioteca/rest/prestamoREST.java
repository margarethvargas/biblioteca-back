package com.cibertec.biblioteca.rest;

import com.cibertec.biblioteca.entidades.Prestamo;
import com.cibertec.biblioteca.negocio.PrestamoNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/prestamo")
public class prestamoREST {
    @Autowired
    private PrestamoNegocio prestamoNegocio;

    @PostMapping("")
    public Prestamo grabar(@RequestBody Prestamo prestamo) {
        return prestamoNegocio.grabar(prestamo);
    }

    @GetMapping("")
    public List<Prestamo> obtener() {
        try {
            return prestamoNegocio.obtener();
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible obtener los datos");
        }
    }

    @PutMapping("")
    public Prestamo actualizar(@RequestBody Prestamo reserva) {
        try{
            return prestamoNegocio.actualizar(reserva);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible actualizar los datos");
        }
    }

    @DeleteMapping("/{id}")
    public Prestamo borrar(@PathVariable(value = "id") Long id) {
        return prestamoNegocio.borrar(id);
    }

    @PostMapping("/desde-reserva/{idReserva}")
    public Prestamo crearDesdeReserva(@PathVariable(value = "idReserva") Long idReserva) {
        try {
            Prestamo prestamo = prestamoNegocio.crearPrestamoDesdeReserva(idReserva);
            if (prestamo == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede crear préstamo desde esta reserva");
            }
            return prestamo;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear préstamo");
        }
    }

    @PutMapping("/devolver/{id}")
    public Prestamo devolverLibro(@PathVariable(value = "id") Long id,
                                  @RequestParam String fechaDevolucion) {
        try {
            Prestamo prestamo = prestamoNegocio.devolverLibro(id, fechaDevolucion);
            if (prestamo == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede devolver este préstamo");
            }
            return prestamo;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al devolver libro");
        }
    }

    @GetMapping("/activos")
    public List<Prestamo> obtenerPrestamosActivos() {
        try {
            return prestamoNegocio.obtenerPrestamosActivos();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible obtener préstamos activos");
        }
    }

    @GetMapping("/vencidos")
    public List<Prestamo> obtenerPrestamosVencidos() {
        try {
            return prestamoNegocio.obtenerPrestamosVencidos();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible obtener préstamos vencidos");
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Prestamo> obtenerPrestamosPorUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        try {
            return prestamoNegocio.obtenerPrestamosPorUsuario(idUsuario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible obtener préstamos del usuario");
        }
    }
}
