package co.epvtecnologia.sistemapos.controller;

import co.epvtecnologia.sistemapos.model.CategoriaDTO;
import co.epvtecnologia.sistemapos.service.CategoriaService;
import co.epvtecnologia.sistemapos.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(final CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "categoria/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("categoria") final CategoriaDTO categoriaDTO) {
        return "categoria/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("categoria") @Valid final CategoriaDTO categoriaDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("codigo") && categoriaDTO.getCodigo() != null && categoriaService.codigoExists(categoriaDTO.getCodigo())) {
            bindingResult.rejectValue("codigo", "Exists.categoria.codigo");
        }
        if (!bindingResult.hasFieldErrors("nombre") && categoriaService.nombreExists(categoriaDTO.getNombre())) {
            bindingResult.rejectValue("nombre", "Exists.categoria.nombre");
        }
        if (bindingResult.hasErrors()) {
            return "categoria/add";
        }
        categoriaService.create(categoriaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("categoria.create.success"));
        return "redirect:/categorias";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("categoria", categoriaService.get(id));
        return "categoria/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("categoria") @Valid final CategoriaDTO categoriaDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        final CategoriaDTO currentCategoriaDTO = categoriaService.get(id);
        if (!bindingResult.hasFieldErrors("codigo") && categoriaDTO.getCodigo() != null &&
                !categoriaDTO.getCodigo().equalsIgnoreCase(currentCategoriaDTO.getCodigo()) &&
                categoriaService.codigoExists(categoriaDTO.getCodigo())) {
            bindingResult.rejectValue("codigo", "Exists.categoria.codigo");
        }
        if (!bindingResult.hasFieldErrors("nombre") &&
                !categoriaDTO.getNombre().equalsIgnoreCase(currentCategoriaDTO.getNombre()) &&
                categoriaService.nombreExists(categoriaDTO.getNombre())) {
            bindingResult.rejectValue("nombre", "Exists.categoria.nombre");
        }
        if (bindingResult.hasErrors()) {
            return "categoria/edit";
        }
        categoriaService.update(id, categoriaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("categoria.update.success"));
        return "redirect:/categorias";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = categoriaService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            categoriaService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("categoria.delete.success"));
        }
        return "redirect:/categorias";
    }

}
