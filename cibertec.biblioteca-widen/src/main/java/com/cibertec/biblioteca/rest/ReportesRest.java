package com.cibertec.biblioteca.rest;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.cibertec.biblioteca.entidades.Libro;
import com.cibertec.biblioteca.entidades.Reserva;
import com.cibertec.biblioteca.entidades.Usuario;
import com.cibertec.biblioteca.negocio.LibroNegocio;
import com.cibertec.biblioteca.negocio.ReservaNegocio;
import com.cibertec.biblioteca.negocio.UsuarioNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReportesRest {

    @Autowired
    private LibroNegocio libroNegocio;

    @Autowired
    private UsuarioNegocio usuarioNegocio;

    @Autowired
    private ReservaNegocio reservaNegocio;

    @GetMapping("/estadisticas-generales")
    public ResponseEntity<Map<String, Object>> obtenerEstadisticasGenerales() {
        try {
            List<Libro> libros = libroNegocio.obtener();
            List<Usuario> usuarios = usuarioNegocio.obtener();
            List<Reserva> reservas = reservaNegocio.obtener();

            Map<String, Object> estadisticas = Map.of(
                "totalLibros", libros.size(),
                "totalUsuarios", usuarios.size(),
                "totalReservas", reservas.size(),
                "reservasActivas", reservas.stream().filter(r -> "APROBADO".equals(r.getEstado_reserva()) || "RESERVADO".equals(r.getEstado_reserva())).count()
            );

            return ResponseEntity.ok(estadisticas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/libros-mas-prestados")
    public ResponseEntity<List<Map<String, Object>>> obtenerLibrosMasPrestados(@RequestParam(defaultValue = "10") int limite) {
        try {
            List<Reserva> reservas = reservaNegocio.obtener();

            Map<String, Long> conteoPorLibro = reservas.stream()
                .collect(Collectors.groupingBy(
                    r -> r.getLibro().getTitulo(),
                    Collectors.counting()
                ));

            List<Map<String, Object>> resultado = conteoPorLibro.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(limite)
                .map(entry -> {
                    Map<String, Object> map = new java.util.HashMap<>();
                    map.put("titulo", entry.getKey());
                    map.put("cantidadReservas", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/usuarios-mas-activos")
    public ResponseEntity<List<Map<String, Object>>> obtenerUsuariosMasActivos(@RequestParam(defaultValue = "10") int limite) {
        try {
            List<Reserva> reservas = reservaNegocio.obtener();

            Map<String, Long> conteoPorUsuario = reservas.stream()
                .filter(r -> r.getUsuario() != null)
                .collect(Collectors.groupingBy(
                    r -> r.getUsuario().getNombre() + " " + r.getUsuario().getApellido(),
                    Collectors.counting()
                ));

            List<Map<String, Object>> resultado = conteoPorUsuario.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(limite)
                .map(entry -> {
                    Map<String, Object> map = new java.util.HashMap<>();
                    map.put("nombre", entry.getKey());
                    map.put("totalReservas", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/reservas-por-mes")
    public ResponseEntity<List<Map<String, Object>>> obtenerReservasPorMes(@RequestParam(defaultValue = "2025") int year) {
        try {
            List<Reserva> reservas = reservaNegocio.obtener();

            Map<String, Long> reservasPorMes = reservas.stream()
                .filter(r -> {
                    try {
                        LocalDate fecha = LocalDate.parse(r.getFch_solicitud());
                        return fecha.getYear() == year;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .collect(Collectors.groupingBy(r -> {
                    try {
                        LocalDate fecha = LocalDate.parse(r.getFch_solicitud());
                        return fecha.getMonth().name();
                    } catch (Exception e) {
                        return "UNKNOWN";
                    }
                }, Collectors.counting()));

            List<Map<String, Object>> resultado = reservasPorMes.entrySet().stream()
                .map(entry -> {
                    Map<String, Object> map = new java.util.HashMap<>();
                    map.put("mes", entry.getKey());
                    map.put("cantidad", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/prestamos-vencidos")
    public ResponseEntity<List<Reserva>> obtenerPrestamosVencidos() {
        try {
            List<Reserva> reservas = reservaNegocio.obtener();

            List<Reserva> vencidos = reservas.stream()
                .filter(r -> {
                    try {
                        LocalDate fechaFin = LocalDate.parse(r.getFch_fin());
                        return fechaFin.isBefore(LocalDate.now()) &&
                               ("APROBADO".equals(r.getEstado_reserva()) || "RESERVADO".equals(r.getEstado_reserva()));
                    } catch (Exception e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());

            return ResponseEntity.ok(vencidos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/descargar-pdf")
    public ResponseEntity<byte[]> descargarReportePDF() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Título
            document.add(new Paragraph("REPORTE DE BIBLIOTECA MUNICIPAL")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20));

            document.add(new Paragraph("Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginBottom(20));

            // Estadísticas Generales
            document.add(new Paragraph("ESTADÍSTICAS GENERALES")
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(16)
                .setMarginBottom(10));

            List<Libro> libros = libroNegocio.obtener();
            List<Usuario> usuarios = usuarioNegocio.obtener();
            List<Reserva> reservas = reservaNegocio.obtener();

            Table statsTable = new Table(UnitValue.createPercentArray(new float[]{2, 1}))
                .setWidth(UnitValue.createPercentValue(100));

            statsTable.addCell("Total de Libros:");
            statsTable.addCell(String.valueOf(libros.size()));
            statsTable.addCell("Total de Usuarios:");
            statsTable.addCell(String.valueOf(usuarios.size()));
            statsTable.addCell("Total de Reservas:");
            statsTable.addCell(String.valueOf(reservas.size()));
            statsTable.addCell("Reservas Activas:");
            statsTable.addCell(String.valueOf(reservas.stream()
                .filter(r -> "APROBADO".equals(r.getEstado_reserva()) || "RESERVADO".equals(r.getEstado_reserva()))
                .count()));

            document.add(statsTable);
            document.add(new Paragraph("\n"));

            // Libros más prestados
            document.add(new Paragraph("LIBROS MÁS PRESTADOS")
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(16)
                .setMarginBottom(10));

            Map<String, Long> conteoPorLibro = reservas.stream()
                .collect(Collectors.groupingBy(
                    r -> r.getLibro().getTitulo(),
                    Collectors.counting()
                ));

            Table librosTable = new Table(UnitValue.createPercentArray(new float[]{3, 1}))
                .setWidth(UnitValue.createPercentValue(100));

            librosTable.addCell("Título del Libro");
            librosTable.addCell("Reservas");

            conteoPorLibro.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .forEach(entry -> {
                    librosTable.addCell(entry.getKey());
                    librosTable.addCell(String.valueOf(entry.getValue()));
                });

            document.add(librosTable);

            document.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "reporte-biblioteca.pdf");

            return ResponseEntity.ok()
                .headers(headers)
                .body(baos.toByteArray());

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
