package co.epvtecnologia.sistemapos.controller;

import co.epvtecnologia.sistemapos.domain.Categoria;
import co.epvtecnologia.sistemapos.domain.Producto;
import co.epvtecnologia.sistemapos.model.ProductoDTO;
import co.epvtecnologia.sistemapos.repos.CategoriaRepository;
import co.epvtecnologia.sistemapos.service.ProductoService;
import co.epvtecnologia.sistemapos.util.CustomCollectors;
import co.epvtecnologia.sistemapos.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaRepository categoriaRepository;

    public ProductoController(final ProductoService productoService,
            final CategoriaRepository categoriaRepository) {
        this.productoService = productoService;
        this.categoriaRepository = categoriaRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("idCategoriaValues", categoriaRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Categoria::getId, Categoria::getNombre)));
    }

    @GetMapping
    public String list(final Model model) {
       List<ProductoDTO> productoDTOSX = new ArrayList<>();
       List<ProductoDTO> productoDTOS = productoService.findAll();
        productoDTOS.forEach(productoDTO ->{

            Optional<Categoria> optionalCategoria = categoriaRepository.findById(productoDTO.getIdCategoria());
            if (optionalCategoria.isPresent()){
                productoDTO.setNombreCategoria(optionalCategoria.get().getNombre());
            }else {
                productoDTO.setNombreCategoria("SIN CATEGORIA");
            }

            productoDTOSX.add(productoDTO);

        });




        model.addAttribute("productoes", productoDTOSX);
        //model.addAttribute("productoes", listaCompleta(productoService.findAll()));
        return "producto/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("producto") final ProductoDTO productoDTO) {
        return "producto/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("producto") @Valid final ProductoDTO productoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("codigoBarras") && productoService.codigoBarrasExists(productoDTO.getCodigoBarras())) {
            bindingResult.rejectValue("codigoBarras", "Exists.producto.codigoBarras");
        }
        if (!bindingResult.hasFieldErrors("codigoInterno") && productoService.codigoInternoExists(productoDTO.getCodigoInterno())) {
            bindingResult.rejectValue("codigoInterno", "Exists.producto.codigoInterno");
        }
        if (bindingResult.hasErrors()) {
            return "producto/add";
        }
        productoService.create(productoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("producto.create.success"));
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("producto", productoService.get(id));
        return "producto/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("producto") @Valid final ProductoDTO productoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        final ProductoDTO currentProductoDTO = productoService.get(id);
        if (!bindingResult.hasFieldErrors("codigoBarras") &&
                !productoDTO.getCodigoBarras().equalsIgnoreCase(currentProductoDTO.getCodigoBarras()) &&
                productoService.codigoBarrasExists(productoDTO.getCodigoBarras())) {
            bindingResult.rejectValue("codigoBarras", "Exists.producto.codigoBarras");
        }
        if (!bindingResult.hasFieldErrors("codigoInterno") &&
                !productoDTO.getCodigoInterno().equalsIgnoreCase(currentProductoDTO.getCodigoInterno()) &&
                productoService.codigoInternoExists(productoDTO.getCodigoInterno())) {
            bindingResult.rejectValue("codigoInterno", "Exists.producto.codigoInterno");
        }
        if (bindingResult.hasErrors()) {
            return "producto/edit";
        }
        productoService.update(id, productoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("producto.update.success"));
        return "redirect:/productos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        productoService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("producto.delete.success"));
        return "redirect:/productos";
    }



}
