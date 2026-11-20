package org.utn.facturacion.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lista_precio_articulo", schema = "modelo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class ListaPrecioArticulo extends AuditoriaApp{
    @ManyToOne
    @JoinColumn(name = "lista_precio_id", nullable = false)
    private ListaPrecio listaPrecio;

    @Column(name = "precioventa", nullable = false)
    private double precioVenta;

    @ManyToOne
    @JoinColumn(name = "articulo_id", nullable = false)
    private Articulo articulo;


}
