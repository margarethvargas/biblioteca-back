package com.cibertec.biblioteca.negocio;

import com.cibertec.biblioteca.entidades.Reserva;
import com.cibertec.biblioteca.repositorio.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaNegocio {
    @Autowired
    private ReservaRepositorio reservaRepositorio;

    public Reserva grabar(Reserva reserva){
        return reservaRepositorio.save(reserva);
    }

    public List<Reserva> obtener(){
        return(List<Reserva>) reservaRepositorio.findAll();
    }

    public Reserva actualizar(Reserva reserva){
        Reserva c = reservaRepositorio.findById(reserva.getId()).get();
        if(c!=null){
            return reservaRepositorio.save(reserva);
        }else {return null;}
    }

    public Reserva borrar(Long id){
        Reserva c = reservaRepositorio.findById(id).orElse(null);
        if(c!=null){
            // Verificar que la reserva no tenga un préstamo activo
            if (c.getPrestamo() != null && "ACTIVO".equals(c.getPrestamo().getEstado_prestamo())) {
                throw new RuntimeException("No se puede cancelar una reserva que tiene un préstamo activo");
            }
            reservaRepositorio.delete(c);
        }else{return null;}
        return c;
    }

    public List<Reserva> obtenerPorIdUsuario(Long idUsuario) {
        return reservaRepositorio.findByUsuarioId(idUsuario);
    }

    public Reserva cambiarEstado(Long id, String nuevoEstado) {
        Reserva reserva = reservaRepositorio.findById(id).orElse(null);
        if (reserva != null) {
            reserva.setEstado_reserva(nuevoEstado);
            return reservaRepositorio.save(reserva);
        }
        return null;
    }

    public Reserva obtenerPorId(Long id) {
        return reservaRepositorio.findById(id).orElse(null);
    }
}
