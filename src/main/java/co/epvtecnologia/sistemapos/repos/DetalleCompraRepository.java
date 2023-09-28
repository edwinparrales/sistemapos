package co.epvtecnologia.sistemapos.repos;

import co.epvtecnologia.sistemapos.domain.Compra;
import co.epvtecnologia.sistemapos.domain.DetalleCompra;
import co.epvtecnologia.sistemapos.model.CompraDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra,Long> {

      // List<DetalleCompra> findByCompra(Compra compra);

}
