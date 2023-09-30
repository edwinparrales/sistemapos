package co.epvtecnologia.sistemapos.service;

import co.epvtecnologia.sistemapos.domain.Categoria;
import co.epvtecnologia.sistemapos.domain.DetalleCompra;
import co.epvtecnologia.sistemapos.domain.Producto;
import co.epvtecnologia.sistemapos.model.ProductoDTO;
import co.epvtecnologia.sistemapos.repos.CategoriaRepository;
import co.epvtecnologia.sistemapos.repos.DetalleCompraRepository;
import co.epvtecnologia.sistemapos.repos.ProductoRepository;
import co.epvtecnologia.sistemapos.util.NotFoundException;
import java.util.List;

import co.epvtecnologia.sistemapos.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    public ProductoService(final ProductoRepository productoRepository,
            final CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<ProductoDTO> findAll() {
        final List<Producto> productoes = productoRepository.findAll(Sort.by("id"));
        return productoes.stream()
                .map(producto -> mapToDTO(producto, new ProductoDTO()))
                .toList();
    }

    public ProductoDTO get(final Long id) {
        return productoRepository.findById(id)
                .map(producto -> mapToDTO(producto, new ProductoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProductoDTO productoDTO) {
        final Producto producto = new Producto();
        mapToEntity(productoDTO, producto);
        return productoRepository.save(producto).getId();
    }

    public void update(final Long id, final ProductoDTO productoDTO) {
        final Producto producto = productoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(productoDTO, producto);
        productoRepository.save(producto);
    }

    public void delete(final Long id) {
        productoRepository.deleteById(id);
    }

    public ProductoDTO mapToDTO(final Producto producto, final ProductoDTO productoDTO) {
        productoDTO.setId(producto.getId());
        productoDTO.setCodigoBarras(producto.getCodigoBarras());
        productoDTO.setCodigoInterno(producto.getCodigoInterno());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setValor(producto.getValor());
        productoDTO.setCantidad(producto.getCantidad());
        productoDTO.setUrlImagen(producto.getUrlImagen());
        productoDTO.setIdCategoria(producto.getIdCategoria());

        return productoDTO;
    }

    public Producto mapToEntity(final ProductoDTO productoDTO, final Producto producto) {
        producto.setId(productoDTO.getId());
        producto.setCodigoBarras(productoDTO.getCodigoBarras());
        producto.setCodigoInterno(productoDTO.getCodigoInterno());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setValor(productoDTO.getValor());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setUrlImagen(productoDTO.getUrlImagen());
        final Categoria idCategoria = productoDTO.getIdCategoria() == null ? null : categoriaRepository.findById(productoDTO.getIdCategoria())
                .orElseThrow(() -> new NotFoundException("idCategoria not found"));
        producto.setIdCategoria(idCategoria.getId());
        return producto;
    }

    public boolean codigoBarrasExists(final String codigoBarras) {
        return productoRepository.existsByCodigoBarrasIgnoreCase(codigoBarras);
    }

    public boolean codigoInternoExists(final String codigoInterno) {
        return productoRepository.existsByCodigoInternoIgnoreCase(codigoInterno);
    }

    //Verificar referencias del producto

    public String referenciaConDetalleCompra(Long idProducto){

        final Producto producto = detalleCompraRepository.findFirstByIdProducto(idProducto).getProducto();
        if (producto != null) {
            return WebUtils.getMessage("El producto no se puede eliminar", producto.getId());
        }
        return null;



    }

   //Referencias con DetalleFactura

    public  ProductoDTO findByCodigoBarras(String codBarras){

        Producto producto = productoRepository.findByCodigoBarras(codBarras);

        return mapToDTO(producto,new ProductoDTO());

    }

    public  ProductoDTO findByCodigoInterno(String codInterno){

        Producto producto = productoRepository.findByCodigoInterno(codInterno);

        return mapToDTO(producto,new ProductoDTO());

    }

}
