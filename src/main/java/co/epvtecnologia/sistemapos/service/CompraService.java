package co.epvtecnologia.sistemapos.service;

import co.epvtecnologia.sistemapos.domain.Compra;
import co.epvtecnologia.sistemapos.domain.Producto;
import co.epvtecnologia.sistemapos.model.CompraDTO;
import co.epvtecnologia.sistemapos.model.ProductoDTO;
import co.epvtecnologia.sistemapos.repos.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public List<CompraDTO>  getListCompraDTO(){

        final List<Compra> compras = compraRepository.findAll(Sort.by("rowid"));
        return compras.stream()
                .map(compra -> mapToCompraDTO(compra, new CompraDTO()))
                .toList();

    }



    public CompraDTO mapToCompraDTO(Compra compraEntity,CompraDTO compraDTO){
        compraDTO.setRowid(compraEntity.getRowid());
        compraDTO.setCodigoFactuar(compraEntity.getCodigoFactuar());
        compraDTO.setFechaRegistro(compraEntity.getFechaRegistro());
        compraDTO.setNitProveedor(compraEntity.getNitProveedor());
        compraDTO.setRazonSocialProveedor(compraEntity.getRazonSocialProveedor());
        //compraDTO.setDetalleCompraDTOList(compraEntity.getDetalleCompraList());

        compraDTO.setValorTotal(compraEntity.getValorTotal());

        return compraDTO;


    }

    public Compra mapToCompraEntity(Compra compraEntity,CompraDTO compraDTO){
        compraEntity.setRowid(compraDTO.getRowid());
        compraEntity.setCodigoFactuar(compraDTO.getCodigoFactuar());
        compraEntity.setFechaRegistro(compraDTO.getFechaRegistro());
        compraEntity.setNitProveedor(compraDTO.getNitProveedor());
        compraEntity.setRazonSocialProveedor(compraDTO.getRazonSocialProveedor());
        //compraEntity.setDetalleCompraDTOList(compraDTO.getDetalleCompraList());

        compraEntity.setValorTotal(compraDTO.getValorTotal());
        return compraEntity;
    }
}
