package co.epvtecnologia.sistemapos.service;

import co.epvtecnologia.sistemapos.domain.IngresoProducto;
import co.epvtecnologia.sistemapos.model.IngresoProductoDTO;
import co.epvtecnologia.sistemapos.model.ProductoDTO;
import co.epvtecnologia.sistemapos.repos.IngresoProductoRepository;
import co.epvtecnologia.sistemapos.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngresoProductoService {

    @Autowired
    private IngresoProductoRepository ingresoProductoRepository;

    public List<IngresoProductoDTO> listar(){
        List<IngresoProducto> listIngresoProducto = ingresoProductoRepository.findAll();

         return  listIngresoProducto.stream().map(ingresoProducto -> {
            return mapToDTO(ingresoProducto,new IngresoProductoDTO());
         }).toList();

    }

    public Long guardar(IngresoProductoDTO ingresoProductoDTO){
        IngresoProducto ingresoProducto = mapToEntity(new IngresoProducto(),ingresoProductoDTO);
        return ingresoProductoRepository.save(ingresoProducto).getId();
    }

   public IngresoProductoDTO findByIdProductoDTO(Long id){

       return ingresoProductoRepository.findById(id)
               .map(ingresoProducto -> mapToDTO(ingresoProducto, new IngresoProductoDTO()))
               .orElseThrow(NotFoundException::new);
   }


    private IngresoProductoDTO mapToDTO(IngresoProducto ingresoProducto,IngresoProductoDTO ingresoProductoDTO){

        ingresoProductoDTO.setId(ingresoProducto.getId());
        ingresoProductoDTO.setCodFactura(ingresoProducto.getCodFactura());
        ingresoProductoDTO.setFecha(ingresoProducto.getFecha());
        ingresoProductoDTO.setNombreProveedor(ingresoProducto.getNombreProveedor());

        return  ingresoProductoDTO;

    }

    private IngresoProducto mapToEntity(IngresoProducto ingresoProducto,IngresoProductoDTO ingresoProductoDTO){
        ingresoProducto.setId(ingresoProductoDTO.getId());
        ingresoProducto.setCodFactura(ingresoProductoDTO.getCodFactura());
        ingresoProducto.setFecha(ingresoProductoDTO.getFecha());
        ingresoProducto.setNombreProveedor(ingresoProductoDTO.getNombreProveedor());

        return ingresoProducto;

    }

}
