package org.utn.facturacion.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "articulo", schema = "modelo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class Articulo extends AuditoriaApp{
    @ManyToOne
    @JoinColumn(name = "rubro_id")
    private Rubro rubro;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String denominacion;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;


}
