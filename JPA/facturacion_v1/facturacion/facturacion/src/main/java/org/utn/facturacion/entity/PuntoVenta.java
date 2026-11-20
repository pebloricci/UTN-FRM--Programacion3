package org.utn.facturacion.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "punto_venta", schema = "modelo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class PuntoVenta extends AuditoriaApp{

    @Column(nullable = false)
    private int numero;

    private String descripcion;
    @Column(name = "tipoemision")
    private String tipoEmision;
    @Column(name = "domiciliocomercial")
    private String domicilioComercial;


}
