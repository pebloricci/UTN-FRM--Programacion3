package com.practico.jpa.entidades;

import jakarta.persistence.*;

@Entity
public class HistoriaClinica extends Base {

    private String descripcion;

    // 1 a 1 unidireccional: la FK paciente_id queda en la tabla historia_clinica
    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public HistoriaClinica() {}

    public HistoriaClinica(String descripcion, Paciente paciente) {
        this.descripcion = descripcion;
        this.paciente = paciente;
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
}
