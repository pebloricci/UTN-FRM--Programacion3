package org.utn.facturacion.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lista_precio", schema = "modelo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class ListaPrecio extends AuditoriaApp{

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String denominacion;


}
