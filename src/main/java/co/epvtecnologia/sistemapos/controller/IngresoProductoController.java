package co.epvtecnologia.sistemapos.controller;

import co.epvtecnologia.sistemapos.domain.IngresoProducto;
import co.epvtecnologia.sistemapos.domain.Producto;
import co.epvtecnologia.sistemapos.model.DetalleIngresoProductoDTO;
import co.epvtecnologia.sistemapos.model.IngresoProductoDTO;
import co.epvtecnologia.sistemapos.model.ProductoDTO;
import co.epvtecnologia.sistemapos.service.IngresoProductoService;
import co.epvtecnologia.sistemapos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ingreso-productos")
public class IngresoProductoController
{

    @Autowired
    private IngresoProductoService ingresoProductoService;
    @Autowired
    private ProductoService productoService;
    private DetalleIngresoProductoDTO detalleIngresoProductoDTO;
    private List<DetalleIngresoProductoDTO> listDetalleIngresoProductoDTOS;
    @GetMapping("/add")
    public String inicio(final Model model,@ModelAttribute("ingresoProductoDTO") final IngresoProductoDTO ingresoProductoDTO){

        return "ingreso-producto/ingresoInventario";
    }


    @PostMapping("/add")
    private String guardar(@ModelAttribute("ingresoProductoDTO") final IngresoProductoDTO ingresoProductoDTO,
                           final BindingResult bindingResult, final RedirectAttributes redirectAttributes){

            //validaciones


          Long id = ingresoProductoService.guardar(ingresoProductoDTO);

         return "redirect:/ingreso-productos/frmdetalle/"+id;

    }

    @GetMapping("/frmdetalle/{id}")

    public String frmDetalleIngresoProducto(@PathVariable final Long id,final Model model){

        IngresoProductoDTO ingresoProductoDTO = ingresoProductoService.findByIdProductoDTO(id);
        model.addAttribute("ingreosProductoDTO",ingresoProductoDTO);


        return "ingreso-producto/ingreso-pro";

    }


    @GetMapping("/buscarCodigo/{cod}")
    @ResponseBody
    public ResponseEntity<ProductoDTO> buscarProductoCodigo(@PathVariable Long cod){

        return ResponseEntity.ok(productoService.get(cod));
    }


}
