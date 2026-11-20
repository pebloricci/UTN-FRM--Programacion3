package com.practico.jpa.entidades;

import jakarta.persistence.*;

/**
 * Superclase abstracta. NO genera tabla propia: sus campos
 * (id, eliminado) se copian en la tabla de cada entidad hija.
 */
@MappedSuperclass
public abstract class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean eliminado = false;

    // Borrado lógico: no borra la fila, solo la marca como eliminada
    public void eliminar() {
        this.eliminado = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Boolean getEliminado() { return eliminado; }
    public void setEliminado(Boolean eliminado) { this.eliminado = eliminado; }
}
