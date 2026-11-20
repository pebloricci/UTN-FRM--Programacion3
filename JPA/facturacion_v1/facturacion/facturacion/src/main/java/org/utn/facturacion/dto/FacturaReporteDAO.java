package org.utn.facturacion.dto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class FacturaReporteDAO {

    public List<FacturaReporteDTO> obtenerReporteFacturas(EntityManager em){
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


}
