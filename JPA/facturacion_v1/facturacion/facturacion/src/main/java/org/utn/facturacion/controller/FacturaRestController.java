package org.utn.facturacion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.utn.facturacion.dto.FacturaReporteDTO;
import org.utn.facturacion.service.FacturaService;

import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaRestController {
    private final FacturaService facturaService;

    @GetMapping
    public List<FacturaReporteDTO> getFacturas(){
        return facturaService.obtenerReporteFacturas();
    }

    @GetMapping("/filtradas")
    public List<FacturaReporteDTO> getFacturasFiltradas(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date fechaDesde,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaHasta,

            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Double montoMinimo
    ){
        return facturaService.obtenerReporteFacturas(fechaDesde, fechaHasta, estado, montoMinimo);
    }
}
