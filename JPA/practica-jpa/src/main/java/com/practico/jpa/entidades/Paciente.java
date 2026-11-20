package com.practico.jpa.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Paciente extends Base {

    private String nombre;
    private String apellido;
    private int edad;
    private int dni;
    private String obraSocial;
    private LocalDate fechaNacimiento;
    private char sexo;

    // N a N unidireccional: JPA crea la tabla intermedia paciente_medicamento
    @ManyToMany
    @JoinTable(name = "paciente_medicamento",
            joinColumns = @JoinColumn(name = "paciente_id"),
            inverseJoinColumns = @JoinColumn(name = "medicamento_id"))
    private List<Medicamento> medicamentos = new ArrayList<>();

    // 1 a N unidireccional: la FK paciente_id queda en la tabla consulta
    @OneToMany
    @JoinColumn(name = "paciente_id")
    private List<Consulta> consultas = new ArrayList<>();

    public Paciente() {}

    public Paciente(String nombre, String apellido, int edad, int dni,
                    String obraSocial, LocalDate fechaNacimiento, char sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.obraSocial = obraSocial;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public int getDni() { return dni; }
    public void setDni(int dni) { this.dni = dni; }
    public String getObraSocial() { return obraSocial; }
    public void setObraSocial(String obraSocial) { this.obraSocial = obraSocial; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate f) { this.fechaNacimiento = f; }
    public char getSexo() { return sexo; }
    public void setSexo(char sexo) { this.sexo = sexo; }
    public List<Medicamento> getMedicamentos() { return medicamentos; }
    public List<Consulta> getConsultas() { return consultas; }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (DNI " + dni + ", " + edad + " años, " + obraSocial + ")";
    }
}
