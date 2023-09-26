package co.epvtecnologia.sistemapos.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class CategoriaDTO {

    private Long id;

    @Size(max = 255)
    private String codigo;

    @NotNull
    @Size(max = 255)
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

}
