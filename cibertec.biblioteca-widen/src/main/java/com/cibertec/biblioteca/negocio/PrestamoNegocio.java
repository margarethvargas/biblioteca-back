package com.cibertec.biblioteca.negocio;

import com.cibertec.biblioteca.entidades.Prestamo;
import com.cibertec.biblioteca.entidades.Reserva;
import com.cibertec.biblioteca.repositorio.PrestamoRepositorio;
import com.cibertec.biblioteca.repositorio.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestamoNegocio {
    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    public Prestamo grabar(Prestamo reserva){
        return prestamoRepositorio.save(reserva);
    }

    public List<Prestamo> obtener(){
        return(List<Prestamo>) prestamoRepositorio.findAll();
    }

    public Prestamo actualizar(Prestamo reserva){
        Prestamo c = prestamoRepositorio.findById(reserva.getId()).get();
        if(c!=null){
            return prestamoRepositorio.save(reserva);
        }else {return null;}
    }

    public Prestamo borrar(Long id){
        Prestamo c = prestamoRepositorio.findById(id).get();
        if(c!=null){
            prestamoRepositorio.delete(c);
        }else{return null;}
        return c;
    }

    public Prestamo crearPrestamoDesdeReserva(Long idReserva) {
        Reserva reserva = reservaRepositorio.findById(idReserva).orElse(null);
        if (reserva == null || !"APROBADA".equals(reserva.getEstado_reserva())) {
            return null;
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(reserva.getUsuario());
        prestamo.setReserva(reserva);

        // Fecha de salida es hoy
        LocalDate hoy = LocalDate.now();
        prestamo.setFch_salida_libro(hoy.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // Fecha de devolución estimada: 15 días después
        LocalDate fechaDevolucion = hoy.plusDays(15);
        prestamo.setFch_devolucion_estimada(fechaDevolucion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        prestamo.setEstado_prestamo("ACTIVO");

        return prestamoRepositorio.save(prestamo);
    }

    public Prestamo devolverLibro(Long idPrestamo, String fechaDevolucionReal) {
        Prestamo prestamo = prestamoRepositorio.findById(idPrestamo).orElse(null);
        if (prestamo == null || !"ACTIVO".equals(prestamo.getEstado_prestamo())) {
            return null;
        }

        prestamo.setFch_devolucion_real(fechaDevolucionReal);
        prestamo.setEstado_prestamo("DEVUELTO");

        return prestamoRepositorio.save(prestamo);
    }

    public List<Prestamo> obtenerPrestamosActivos() {
        List<Prestamo> todos = (List<Prestamo>) prestamoRepositorio.findAll();
        return todos.stream()
                .filter(prestamo -> "ACTIVO".equals(prestamo.getEstado_prestamo()))
                .collect(Collectors.toList());
    }

    public List<Prestamo> obtenerPrestamosVencidos() {
        LocalDate hoy = LocalDate.now();
        List<Prestamo> todos = (List<Prestamo>) prestamoRepositorio.findAll();
        return todos.stream()
                .filter(prestamo -> "ACTIVO".equals(prestamo.getEstado_prestamo()))
                .filter(prestamo -> {
                    if (prestamo.getFch_devolucion_estimada() != null) {
                        LocalDate fechaEstimada = LocalDate.parse(prestamo.getFch_devolucion_estimada());
                        return hoy.isAfter(fechaEstimada);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public List<Prestamo> obtenerPrestamosPorUsuario(Long idUsuario) {
        List<Prestamo> todos = (List<Prestamo>) prestamoRepositorio.findAll();
        return todos.stream()
                .filter(prestamo -> prestamo.getUsuario() != null && prestamo.getUsuario().getId() == idUsuario)
                .collect(Collectors.toList());
    }
}
