package co.epvtecnologia.sistemapos.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "DETALLE_INGRESO_PRODUCTOS")
public class DetalleIngresoProducto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idDetalleIngProducto;
    private int cantidadAnterior;
    private int cantidad;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ingreso_producto")
    private IngresoProducto ingresoProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private Producto producto;




    public Long getIdDetalleIngProducto() {
        return idDetalleIngProducto;
    }

    public void setIdDetalleIngProducto(Long idDetalleIngProducto) {
        this.idDetalleIngProducto = idDetalleIngProducto;
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

    public IngresoProducto getIngresoProducto() {
        return ingresoProducto;
    }

    public void setIngresoProducto(IngresoProducto ingresoProducto) {
        this.ingresoProducto = ingresoProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
