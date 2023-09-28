package co.epvtecnologia.sistemapos.repos;

import co.epvtecnologia.sistemapos.domain.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra,Long> {


}
