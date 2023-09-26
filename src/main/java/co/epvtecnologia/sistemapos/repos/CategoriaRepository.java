package co.epvtecnologia.sistemapos.repos;

import co.epvtecnologia.sistemapos.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByCodigoIgnoreCase(String codigo);

    boolean existsByNombreIgnoreCase(String nombre);

}
