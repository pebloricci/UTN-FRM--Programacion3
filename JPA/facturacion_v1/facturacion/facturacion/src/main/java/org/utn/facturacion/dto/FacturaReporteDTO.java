package org.utn.facturacion.dto;
import lombok.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaReporteDTO {
    private Long numeroFactura;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date fechaEmision;
    private String clienteDenominacion;
    private String condicionIva;
    private String puntoVentaDescripcion;
    private double importeTotal;
    private long cantidadItems;

    @Override
    public String toString() {
        return "FacturaReporteDTO{" +
                "numeroFactura=" + numeroFactura +
                ", fechaEmision=" + fechaEmision +
                ", clienteDenominacion='" + clienteDenominacion + '\'' +
                ", condicionIva='" + condicionIva + '\'' +
                ", puntoVentaDescripcion='" + puntoVentaDescripcion + '\'' +
                ", importeTotal=" + importeTotal +
                ", cantidadItems=" + cantidadItems +
                '}';
    }
}


