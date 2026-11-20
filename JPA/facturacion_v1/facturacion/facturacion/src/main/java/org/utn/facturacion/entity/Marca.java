package org.utn.facturacion.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "marca", schema = "modelo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class Marca extends AuditoriaApp{
    @Column(nullable = false)
    private String demonicion;

    @Column(nullable = false)
    private Integer codigo;


}
