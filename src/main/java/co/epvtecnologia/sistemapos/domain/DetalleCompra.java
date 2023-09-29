package co.epvtecnologia.sistemapos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "DETALLE_COMPRAS")
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long rowid;
    private int cantidadAnterior;
    private int cantidad;

    @Column(name = "id_compra")
    private Long idCompra;

    @Column(name = "id_producto")
    private Long idProducto;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_compra",updatable = false,insertable = false)
    private Compra compra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto",updatable = false,insertable = false)
    private Producto producto;


    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid= rowid;
    }

    public int getCantidadAnterior() {
        return cantidadAnterior;
    }

    public void setCantidadAnterior(int cantidadAnterior) {
        this.cantidadAnterior = cantidadAnterior;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }



}
