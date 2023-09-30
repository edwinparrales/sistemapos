package co.epvtecnologia.sistemapos.repos;

import co.epvtecnologia.sistemapos.domain.Categoria;
import co.epvtecnologia.sistemapos.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Producto findFirstByIdCategoria(Long idCategoria);

    boolean existsByCodigoBarrasIgnoreCase(String codigoBarras);

    boolean existsByCodigoInternoIgnoreCase(String codigoInterno);

    Producto findByCodigoBarras(String codBarras);
    Producto findByCodigoInterno(String codInterno);

}
