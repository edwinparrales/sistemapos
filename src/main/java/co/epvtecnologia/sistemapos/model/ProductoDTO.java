package co.epvtecnologia.sistemapos.model;

import co.epvtecnologia.sistemapos.domain.Categoria;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class ProductoDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String codigoBarras;

    @NotNull
    @Size(max = 255)
    private String codigoInterno;
    @NotNull
    @Size(max = 255,min = 5)
    private String descripcion;

    @NotNull
    private Double valor;

    @NotNull

    private Long cantidad;

    @Size(max = 255)
    private String urlImagen;
    @NotNull
    private Long idCategoria;

    private String nombreCategoria;



    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(final String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(final String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(final Double valor) {
        this.valor = valor;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(final Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(final String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(final Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
