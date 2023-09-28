package co.epvtecnologia.sistemapos.model;

import co.epvtecnologia.sistemapos.domain.Compra;
import co.epvtecnologia.sistemapos.domain.Producto;
import jakarta.persistence.*;

public class DetalleCompraDTO {



    private Long rowid;
    private int cantidadAnterior;
    private int cantidad;
    private Compra compra;
    private Producto producto;


    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
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
