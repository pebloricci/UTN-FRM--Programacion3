package org.utn.facturacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "factura_venta",schema = "modelo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true, exclude = {"detalles"})
@ToString(exclude = {"detalles"})
public class FacturaVenta extends AuditoriaApp{
    private Long numero;

    @Column(name = "fechaemision", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;

    @ManyToOne
    @JoinColumn(name = "punto_venta_id", nullable = false)
    private PuntoVenta puntoVenta;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "condicion_iva_id", nullable = false)
    private CondicionIVA condicionIva;

    @ManyToOne
    @JoinColumn(name = "tipo_moneda_id", nullable = false)
    private TipoMoneda tipoMoneda;
    @Column(name = "importecobrado")
    private double importeCobrado;
    @Column(name = "importesaldo")
    private double importeSaldo;

    @Column(name = "importetotal", nullable = false)
    private double importeTotal;

    private String cae;
    @Column(name = "caefechavencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date caeFechaVencimiento;
    @Column(name = "resultadoafip")
    private String resultadoAfip;
    @Column(name = "motivorechazo")
    private String motivoRechazo;

    @Column(nullable = false)
    private String estado;
    @Column(name = "fechaanulacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulacion;

    private String observaciones;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FacturaVentaDetalle> detalles = new ArrayList<>();

    public void addDetalle(FacturaVentaDetalle detalle){
        detalles.add(detalle);
        detalle.setFactura(this);
    }


}
