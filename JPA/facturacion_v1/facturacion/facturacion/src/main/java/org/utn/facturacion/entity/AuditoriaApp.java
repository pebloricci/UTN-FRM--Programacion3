package org.utn.facturacion.entity;
import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString

public abstract class AuditoriaApp extends EntityId {

    @Column(name = "fechaalta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fechaAlta;

    @Column(name="fechabaja", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fechaBaja;

    @Column(name = "fechamodificacion",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "usuario_carga_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    protected Usuario usuarioCarga;

    @ManyToOne
    @JoinColumn(name = "usuario_baja_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    protected Usuario usuarioBaja;

    @ManyToOne
    @JoinColumn(name = "usuario_modificacion_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    protected Usuario usuarioModificacion;


}
