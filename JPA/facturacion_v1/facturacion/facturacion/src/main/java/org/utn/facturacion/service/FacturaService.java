package org.utn.facturacion.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.utn.facturacion.dto.FacturaReporteDTO;

import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacturaService {
    @PersistenceContext
    private EntityManager em;
    public List<FacturaReporteDTO> obtenerReporteFacturas(){
        String jpql = "Select new org.utn.facturacion.dto.FacturaReporteDTO(" +
                "f.numero, " +
                "f.fechaEmision, " +
                "COALESCE(c.denominacion, 'Consumidor Final'), " +
                "ci.denominacion, " +
                "pv.descripcion, " +
                "f.importeTotal, " +
                "COUNT(d) " +
                ")" +
                "From FacturaVenta f " +
                "left join f.cliente c " +
                "join f.condicionIva ci " +
                "join f.puntoVenta pv " +
                "join f.detalles d " +
                "group by f.id, f.numero, f.fechaEmision, c.denominacion, " +
                "ci.denominacion, pv.descripcion, f.importeTotal";
        return em.createQuery(jpql, FacturaReporteDTO.class).getResultList();

    }
    public List<FacturaReporteDTO> obtenerReporteFacturas(
            Date fechaDesde, Date fechaHasta, String estado, Double montoMinimo) {

        StringBuilder jpql = new StringBuilder(
                "SELECT new org.utn.facturacion.dto.FacturaReporteDTO(" +
                        "f.numero, f.fechaEmision, COALESCE(c.denominacion, 'Consumidor Final'), " +
                        "ci.denominacion, pv.descripcion, f.importeTotal, COUNT(d)) " +
                        "FROM FacturaVenta f " +
                        "LEFT JOIN f.cliente c " +
                        "JOIN f.condicionIva ci " +
                        "JOIN f.puntoVenta pv " +
                        "JOIN f.detalles d WHERE 1=1 "
        );

        Map<String, Object> params = new HashMap<>();

        if (fechaDesde != null) {
            jpql.append(" AND f.fechaEmision >= :fechaDesde");
            params.put("fechaDesde", fechaDesde);
        }
        if (fechaHasta != null) {
            jpql.append(" AND f.fechaEmision <= :fechaHasta");
            params.put("fechaHasta", fechaHasta);
        }
        if (estado != null) {
            jpql.append(" AND f.estado = :estado");
            params.put("estado", estado);

        }
        if (montoMinimo != null) {
            jpql.append(" AND f.importeTotal >= :montoMinimo");
            params.put("montoMinimo", montoMinimo);
        }

        jpql.append(" GROUP BY f.id, f.numero, f.fechaEmision, c.denominacion, " +
                "ci.denominacion, pv.descripcion, f.importeTotal");

        var query = em.createQuery(jpql.toString(), FacturaReporteDTO.class);
        params.forEach(query::setParameter);

        return query.getResultList();
    }

}