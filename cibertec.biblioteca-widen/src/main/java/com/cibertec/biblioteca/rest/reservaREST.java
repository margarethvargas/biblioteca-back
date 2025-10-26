package com.cibertec.biblioteca.rest;

import com.cibertec.biblioteca.entidades.Reserva;
import com.cibertec.biblioteca.negocio.ReservaNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/reserva")
public class reservaREST {
    @Autowired
    private ReservaNegocio reservaNegocio;

    @PostMapping("")
    public Reserva grabar(@RequestBody Reserva reserva) {
        return reservaNegocio.grabar(reserva);
    }

    @GetMapping("")
    public List<Reserva> obtener() {
        try {
            return reservaNegocio.obtener();
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible obtener los datos");
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Reserva> obtenerPorIdUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        try {
            return reservaNegocio.obtenerPorIdUsuario(idUsuario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible obtener los datos");
        }
    }

    @GetMapping("/lector/{idLector}")
    public List<Reserva> obtenerPorIdLector(@PathVariable(value = "idLector") Long idLector) {
        try {
            return reservaNegocio.obtenerPorIdUsuario(idLector);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible obtener los datos");
        }
    }

    @PutMapping("")
    public Reserva actualizar(@RequestBody Reserva reserva) {
        try{
            return reservaNegocio.actualizar(reserva);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible actualizar los datos");
        }
    }

    @DeleteMapping("/{id}")
    public Reserva borrar(@PathVariable(value = "id") Long id) {
        try {
            Reserva reserva = reservaNegocio.borrar(id);
            if (reserva == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada");
            }
            return reserva;
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}/estado")
    public Reserva cambiarEstado(@PathVariable(value = "id") Long id, @RequestBody Reserva reservaEstado) {
        try {
            return reservaNegocio.cambiarEstado(id, reservaEstado.getEstado_reserva());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al cambiar el estado de la reserva");
        }
    }

    @GetMapping("/{id}")
    public Reserva obtenerPorId(@PathVariable(value = "id") Long id) {
        try {
            Reserva reserva = reservaNegocio.obtenerPorId(id);
            if (reserva == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada");
            }
            return reserva;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada");
        }
    }
}
