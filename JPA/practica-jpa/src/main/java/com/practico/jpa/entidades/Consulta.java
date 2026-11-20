package com.practico.jpa.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Consulta extends Base {

    private LocalDate fecha;
    private String diagnostico;

    public Consulta() {}

    public Consulta(LocalDate fecha, String diagnostico) {
        this.fecha = fecha;
        this.diagnostico = diagnostico;
    }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    @Override
    public String toString() {
        return fecha + " - " + diagnostico;
    }
}
