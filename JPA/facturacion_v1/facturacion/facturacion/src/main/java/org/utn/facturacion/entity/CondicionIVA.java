package org.utn.facturacion.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "condicion_iva", schema = "modelo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class CondicionIVA extends AuditoriaApp{
    @Column(name = "codigoafip", nullable = false)
    private int codigoAfip;

    @Column(nullable = false)
    private String denominacion;
}
