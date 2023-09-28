package co.epvtecnologia.sistemapos.service;

import co.epvtecnologia.sistemapos.domain.Compra;
import co.epvtecnologia.sistemapos.domain.DetalleCompra;
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
    @Autowired
    private ProductoService productoService;

    @Autowired
    private DetalleCompraService detalleCompraService;

    public List<CompraDTO>  getListCompraDTO(){

        final List<Compra> compras = compraRepository.findAll(Sort.by("rowid"));
        return compras.stream()
                .map(compra -> mapToCompraDTO(compra, new CompraDTO()))
                .toList();

    }

    public Long guardar(CompraDTO compraDTO){

        Compra compra = mapToCompraEntity(new Compra(),compraDTO);
        return  compraRepository.save(compra).getRowid();
    }

     public CompraDTO  getCompraDTO(Long rowid){
        Compra compra = compraRepository.findById(rowid).get();

      return  mapToCompraDTO(compra,new CompraDTO());


     }


    public CompraDTO mapToCompraDTO(Compra compraEntity,CompraDTO compraDTO){
        compraDTO.setRowid(compraEntity.getRowid());
        compraDTO.setCodigoFactuar(compraEntity.getCodigoFactuar());
        compraDTO.setFechaRegistro(compraEntity.getFechaRegistro());
        compraDTO.setNitProveedor(compraEntity.getNitProveedor());
        compraDTO.setRazonSocialProveedor(compraEntity.getRazonSocialProveedor());
        compraDTO.setDetalleCompraList(compraEntity.getDetalleCompraList());

        compraDTO.setValorTotal(compraEntity.getValorTotal());

        return compraDTO;


    }

    public Compra mapToCompraEntity(Compra compraEntity,CompraDTO compraDTO){
        compraEntity.setRowid(compraDTO.getRowid());
        compraEntity.setCodigoFactuar(compraDTO.getCodigoFactuar());
        compraEntity.setFechaRegistro(compraDTO.getFechaRegistro());
        compraEntity.setNitProveedor(compraDTO.getNitProveedor());
        compraEntity.setRazonSocialProveedor(compraDTO.getRazonSocialProveedor());
        compraEntity.setDetalleCompraList(compraDTO.getDetalleCompraList());

        compraEntity.setValorTotal(compraDTO.getValorTotal());
        return compraEntity;
    }

    public ProductoDTO buscarProductoCodigo(String codigoInterno) {

        return  productoService.findByCodigoInterno(codigoInterno);
    }

    public ProductoDTO buscarProductoCodigoBarras(String codBarras) {

        return  productoService.findByCodigoBarras(codBarras);
    }


    public DetalleCompra guardarDetalleCompra(DetalleCompra detalleCompra){
        return  detalleCompraService.guardar(detalleCompra);
    }

    public List<DetalleCompra> listaDetalleCompra(){

        return detalleCompraService.listaDetalleCompra();
    }

    public Compra getCompra(Long rowid){

        return compraRepository.findById(rowid).get();
    }



}
