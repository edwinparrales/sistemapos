package co.epvtecnologia.sistemapos.repos;

import co.epvtecnologia.sistemapos.domain.DetalleIngresoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleIngresoProductoRepository extends JpaRepository<DetalleIngresoProducto,Long> {
}
