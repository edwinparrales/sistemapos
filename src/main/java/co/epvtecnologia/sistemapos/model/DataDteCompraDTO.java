package co.epvtecnologia.sistemapos.model;

public class DataDteCompraDTO {

    private Long rowidProducto;
    private Long rowidCompra;

    private Long cantidad;

    private Long cantidadAnterior;

    private String nombreProducto;

    private Double valorProducto;


    public Long getRowidProducto() {
        return rowidProducto;
    }

    public void setRowidProducto(Long rowidProducto) {
        this.rowidProducto = rowidProducto;
    }

    public Long getRowidCompra() {
        return rowidCompra;
    }

    public void setRowidCompra(Long rowidCompra) {
        this.rowidCompra = rowidCompra;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Long getCantidadAnterior() {
        return cantidadAnterior;
    }

    public void setCantidadAnterior(Long cantidadAnterior) {
        this.cantidadAnterior = cantidadAnterior;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(Double valorProducto) {
        this.valorProducto = valorProducto;
    }
}
