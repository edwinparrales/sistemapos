package co.epvtecnologia.sistemapos.model;

import co.epvtecnologia.sistemapos.domain.Compra;
import co.epvtecnologia.sistemapos.domain.Producto;
import jakarta.persistence.*;

public class DetalleCompraDTO {

       private Long rowidProducto;
       private String codigoProducto;
       private String codigoBarrasProducto;
       private String nombreProducto;

       private Long cantidad;

       private double valorTotal;
       private double valorUnidadProducto;
       private Long rowidCompra;
       private Long rowidDetalleCompra;


    public Long getRowidProducto() {
        return rowidProducto;
    }

    public void setRowidProducto(Long rowidProducto) {
        this.rowidProducto = rowidProducto;
    }

    public String getCodigoBarrasProducto() {
        return codigoBarrasProducto;
    }

    public void setCodigoBarrasProducto(String codigoBarrasProducto) {
        this.codigoBarrasProducto = codigoBarrasProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorUnidadProducto() {
        return valorUnidadProducto;
    }

    public void setValorUnidadProducto(double valorUnidadProducto) {
        this.valorUnidadProducto = valorUnidadProducto;
    }

    public Long getRowidCompra() {
        return rowidCompra;
    }

    public void setRowidCompra(Long rowidCompra) {
        this.rowidCompra = rowidCompra;
    }

    public Long getRowidDetalleCompra() {
        return rowidDetalleCompra;
    }

    public void setRowidDetalleCompra(Long rowidDetalleCompra) {
        this.rowidDetalleCompra = rowidDetalleCompra;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
}
