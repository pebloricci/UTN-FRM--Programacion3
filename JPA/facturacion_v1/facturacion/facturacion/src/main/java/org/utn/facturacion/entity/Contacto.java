package org.utn.facturacion.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contacto", schema = "modelo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class Contacto extends EntityId{
    @Column(nullable = false)
    private String email;
    @Column(length = 20)
    private String telefono;
    @Column(length = 20)
    private String celular;


}
