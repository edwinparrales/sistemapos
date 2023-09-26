package co.epvtecnologia.sistemapos.repos;

import co.epvtecnologia.sistemapos.domain.Categoria;
import co.epvtecnologia.sistemapos.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Producto findFirstByIdCategoria(Categoria categoria);

    boolean existsByCodigoBarrasIgnoreCase(String codigoBarras);

    boolean existsByCodigoInternoIgnoreCase(String codigoInterno);

}
