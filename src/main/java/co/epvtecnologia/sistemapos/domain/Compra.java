package co.epvtecnologia.sistemapos.domain;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "COMPRAS")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowid;

    @Column(nullable = false)
    private String codigoFactuar;

    @Column(nullable = false)
    private String razonSocialProveedor;
    @Column(nullable = false)
    private String nitProveedor;


    private LocalDate fechaRegistro;

    private double valorTotal;


    @OneToMany(mappedBy = "compra")
    List<DetalleCompra> detalleCompraList;


    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
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

    public List<DetalleCompra> getDetalleCompraList() {
        return detalleCompraList;
    }

    public void setDetalleCompraList(List<DetalleCompra> detalleCompraList) {
        this.detalleCompraList = detalleCompraList;
    }

    public String getCodigoFactuar() {
        return codigoFactuar;
    }

    public void setCodigoFactuar(String codigoFactuar) {
        this.codigoFactuar = codigoFactuar;
    }
}
