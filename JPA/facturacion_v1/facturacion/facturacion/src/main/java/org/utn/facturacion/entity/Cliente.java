package org.utn.facturacion.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "cliente", schema = "modelo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class Cliente extends AuditoriaApp{

    @Column(name = "cuitcuil", nullable = false)
    private String cuitCuil;

    @Column(nullable = false)
    private String denominacion;

    @OneToOne
    @JoinColumn(name = "contacto_id", nullable = false)
    private Contacto contacto;

    @OneToOne
    @JoinColumn(name = "domicilio_id", nullable = false)
    private Domicilio domicilio;


}
