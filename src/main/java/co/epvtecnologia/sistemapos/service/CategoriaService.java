package co.epvtecnologia.sistemapos.service;

import co.epvtecnologia.sistemapos.domain.Categoria;
import co.epvtecnologia.sistemapos.domain.Producto;
import co.epvtecnologia.sistemapos.model.CategoriaDTO;
import co.epvtecnologia.sistemapos.repos.CategoriaRepository;
import co.epvtecnologia.sistemapos.repos.ProductoRepository;
import co.epvtecnologia.sistemapos.util.NotFoundException;
import co.epvtecnologia.sistemapos.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    public CategoriaService(final CategoriaRepository categoriaRepository,
            final ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    public List<CategoriaDTO> findAll() {
        final List<Categoria> categorias = categoriaRepository.findAll(Sort.by("id"));
        return categorias.stream()
                .map(categoria -> mapToDTO(categoria, new CategoriaDTO()))
                .toList();
    }

    public CategoriaDTO get(final Long id) {
        return categoriaRepository.findById(id)
                .map(categoria -> mapToDTO(categoria, new CategoriaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CategoriaDTO categoriaDTO) {
        final Categoria categoria = new Categoria();
        mapToEntity(categoriaDTO, categoria);
        return categoriaRepository.save(categoria).getId();
    }

    public void update(final Long id, final CategoriaDTO categoriaDTO) {
        final Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(categoriaDTO, categoria);
        categoriaRepository.save(categoria);
    }

    public void delete(final Long id) {
        categoriaRepository.deleteById(id);
    }

    private CategoriaDTO mapToDTO(final Categoria categoria, final CategoriaDTO categoriaDTO) {
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setCodigo(categoria.getCodigo());
        categoriaDTO.setNombre(categoria.getNombre());
        return categoriaDTO;
    }

    private Categoria mapToEntity(final CategoriaDTO categoriaDTO, final Categoria categoria) {
        categoria.setCodigo(categoriaDTO.getCodigo());
        categoria.setNombre(categoriaDTO.getNombre());
        return categoria;
    }

    public boolean codigoExists(final String codigo) {
        return categoriaRepository.existsByCodigoIgnoreCase(codigo);
    }

    public boolean nombreExists(final String nombre) {
        return categoriaRepository.existsByNombreIgnoreCase(nombre);
    }

    public String getReferencedWarning(final Long id) {
        final Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Producto idCategoriaProducto = productoRepository.findFirstByIdCategoria(categoria);
        if (idCategoriaProducto != null) {
            return WebUtils.getMessage("categoria.producto.idCategoria.referenced", idCategoriaProducto.getId());
        }
        return null;
    }

}
