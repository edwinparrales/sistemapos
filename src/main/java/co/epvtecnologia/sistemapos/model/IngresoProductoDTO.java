package co.epvtecnologia.sistemapos.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;

public class IngresoProductoDTO {

    private Long id;

    private String codFactura;

    private String nombreProveedor;

    private LocalDate fecha;


    private ProductoDTO productoDTO;



    private DetalleIngresoProductoDTO detalleIngresoProductoDTO;

    List<DetalleIngresoProductoDTO> detalleIngresoProductoDTOList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(String codFactura) {
        this.codFactura = codFactura;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<DetalleIngresoProductoDTO> getDetalleIngresoProductoDTOList() {
        return detalleIngresoProductoDTOList;
    }

    public void setDetalleIngresoProductoDTOList(List<DetalleIngresoProductoDTO> detalleIngresoProductoDTOList) {
        this.detalleIngresoProductoDTOList = detalleIngresoProductoDTOList;
    }

    public DetalleIngresoProductoDTO getDetalleIngresoProductoDTO() {
        return detalleIngresoProductoDTO;
    }

    public void setDetalleIngresoProductoDTO(DetalleIngresoProductoDTO detalleIngresoProductoDTO) {
        this.detalleIngresoProductoDTO = detalleIngresoProductoDTO;
    }

    public ProductoDTO getProductoDTO() {
        return productoDTO;
    }

    public void setProductoDTO(ProductoDTO productoDTO) {
        this.productoDTO = productoDTO;
    }
}
