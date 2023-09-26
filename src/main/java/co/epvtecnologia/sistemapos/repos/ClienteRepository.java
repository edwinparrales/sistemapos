package co.epvtecnologia.sistemapos.repos;

import co.epvtecnologia.sistemapos.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

       public Cliente findByIdCliente(Long id);
       public boolean existsClienteByNumDocumento(String numDocumento);
}
