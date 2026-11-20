package com.practico.jpa.entidades;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Medico extends Base {

    private String nombre;
    private String apellido;
    private int edad;
    private String especialidad;
    private String matricula;

    // 1 a N unidireccional: la FK medico_id queda en la tabla consulta
    @OneToMany
    @JoinColumn(name = "medico_id")
    private List<Consulta> consultas = new ArrayList<>();

    public Medico() {}

    public Medico(String nombre, String apellido, int edad, String especialidad, String matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.especialidad = especialidad;
        this.matricula = matricula;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public List<Consulta> getConsultas() { return consultas; }

    @Override
    public String toString() {
        return "Dr/a. " + nombre + " " + apellido + " (" + especialidad + ", mat. " + matricula + ")";
    }
}
