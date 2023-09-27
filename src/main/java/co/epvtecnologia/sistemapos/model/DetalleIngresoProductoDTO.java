package co.epvtecnologia.sistemapos.model;

public class DetalleIngresoProductoDTO {

    private Long idDetalleIngProducto;
    private int cantidadAnterior;
    private int cantidad;

    private ProductoDTO productoDTO;

    private Long idIngresoProducto;

    private double valorProducto;
    private double valorTotalRow;

    public Long getIdDetalleIngProducto() {
        return idDetalleIngProducto;
    }

    public void setIdDetalleIngProducto(Long idDetalleIngProducto) {
        this.idDetalleIngProducto = idDetalleIngProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadAnterior() {
        return cantidadAnterior;
    }

    public void setCantidadAnterior(int cantidadAnterior) {
        this.cantidadAnterior = cantidadAnterior;
    }



    public Long getIdIngresoProducto() {
        return idIngresoProducto;
    }

    public void setIdIngresoProducto(Long idIngresoProducto) {
        this.idIngresoProducto = idIngresoProducto;
    }

    public double getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(double valorProducto) {
        this.valorProducto = valorProducto;
    }

    public double getValorTotalRow() {
        return valorTotalRow;
    }

    public void setValorTotalRow(double valorTotalRow) {
        this.valorTotalRow = valorTotalRow;
    }

    public ProductoDTO getProductoDTO() {
        return productoDTO;
    }

    public void setProductoDTO(ProductoDTO productoDTO) {
        this.productoDTO = productoDTO;
    }
}
