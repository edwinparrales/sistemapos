package co.epvtecnologia.sistemapos.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Producto {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigoBarras;

    @Column(nullable = false, unique = true)
    private String codigoInterno;

    @Column
    private String descripcion;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private Long cantidad;

    @Column
    private String urlImagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria_id")
    private Categoria idCategoria;

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

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(final Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

}
