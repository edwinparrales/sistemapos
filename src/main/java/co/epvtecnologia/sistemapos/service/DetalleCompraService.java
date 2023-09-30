package co.epvtecnologia.sistemapos.service;

import co.epvtecnologia.sistemapos.domain.Compra;
import co.epvtecnologia.sistemapos.domain.DetalleCompra;
import co.epvtecnologia.sistemapos.repos.DetalleCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleCompraService {
    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    public List<DetalleCompra> listaDetalleCompra(){

        return  detalleCompraRepository.findAll();

    }


    public DetalleCompra guardar(DetalleCompra detalleCompra){

        return  detalleCompraRepository.save(detalleCompra);
    }


   public List<DetalleCompra> detalleCompraListByRowidCompra(long rowidCompra){

        return detalleCompraRepository.findByIdCompra(rowidCompra);
   }

   public void delete(Long id){
        detalleCompraRepository.deleteById(id);
   }

   public DetalleCompra get(Long id){
        return  detalleCompraRepository.findById(id).get();
   }


}
