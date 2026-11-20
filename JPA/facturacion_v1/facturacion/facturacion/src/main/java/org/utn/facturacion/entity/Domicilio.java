package org.utn.facturacion.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "domicilio", schema = "modelo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class Domicilio extends EntityId{
    @Column(name = "nombrecalle", nullable = false)
    private String nombreCalle;
    @Column(name = "numerocalle", nullable = false)
    private String numeroCalle;


}
