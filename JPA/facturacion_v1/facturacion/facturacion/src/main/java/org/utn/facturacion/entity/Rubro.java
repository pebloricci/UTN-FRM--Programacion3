package org.utn.facturacion.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rubro", schema = "modelo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class Rubro extends AuditoriaApp{
    @Column(nullable = false)
    private String denominacion;

    @Column(nullable = false)
    private Integer codigo;


}
