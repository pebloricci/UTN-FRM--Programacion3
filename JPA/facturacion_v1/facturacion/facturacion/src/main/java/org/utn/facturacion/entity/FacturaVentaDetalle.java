package org.utn.facturacion.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "factura_venta_detalle", schema = "modelo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class FacturaVentaDetalle extends EntityId{

    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false)
    private FacturaVenta factura;

    @ManyToOne
    @JoinColumn(name = "lista_precio_articulo_id", nullable = false)
    private ListaPrecioArticulo listaPrecioArticulo;

    private String descripcion;

    @Column(nullable = false)
    private double cantidad;

    @Column(name = "preciounitario", nullable = false)
    private double precioUnitario;
    @Column(name = "porcentajebonificacion")
    private double porcentajeBonificacion;
    @Column(name = "importeneto")
    private double importeNeto;
    @Column(name = "importeiva")
    private double importeIva;

    @Column(name = "importesubtotal", nullable = false)
    private double importeSubtotal;


}
