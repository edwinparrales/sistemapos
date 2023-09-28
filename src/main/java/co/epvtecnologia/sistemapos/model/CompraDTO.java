package co.epvtecnologia.sistemapos.model;

import co.epvtecnologia.sistemapos.domain.DetalleCompra;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class CompraDTO {

    private Long rowid;

    private String codigoFactuar;


    private String razonSocialProveedor;

    private String nitProveedor;


    private LocalDate fechaRegistro;

    private double valorTotal;

    List<DetalleCompraDTO> detalleCompraDTOList;

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public String getCodigoFactuar() {
        return codigoFactuar;
    }

    public void setCodigoFactuar(String codigoFactuar) {
        this.codigoFactuar = codigoFactuar;
    }

    public String getRazonSocialProveedor() {
        return razonSocialProveedor;
    }

    public void setRazonSocialProveedor(String razonSocialProveedor) {
        this.razonSocialProveedor = razonSocialProveedor;
    }

    public String getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<DetalleCompraDTO> getDetalleCompraDTOListList() {
        return detalleCompraDTOList;
    }

    public void setDetalleCompraDTOList(List<DetalleCompraDTO> detalleCompraDTOList) {
        this.detalleCompraDTOList = detalleCompraDTOList;
    }
}
