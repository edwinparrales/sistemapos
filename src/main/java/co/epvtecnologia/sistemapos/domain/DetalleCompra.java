package co.epvtecnologia.sistemapos.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "DETALLE_COMPRAS")
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long rowid;
    private int cantidadAnterior;
    private int cantidad;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_compra")
    private Compra compra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto")
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
}
