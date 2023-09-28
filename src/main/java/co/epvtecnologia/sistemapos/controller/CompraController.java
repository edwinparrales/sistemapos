package co.epvtecnologia.sistemapos.controller;


import co.epvtecnologia.sistemapos.model.CompraDTO;
import co.epvtecnologia.sistemapos.model.ProductoDTO;
import co.epvtecnologia.sistemapos.service.CompraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/compras")
public class CompraController {
    @Autowired
     private CompraService compraService;
    @GetMapping()
    public String list(final Model model){

        List<CompraDTO> compraDTOList = compraService.getListCompraDTO();

        model.addAttribute("compras",compraDTOList);

        return "compra-producto/list";


    }

    @GetMapping("/add")

    public String add(@ModelAttribute("compra") CompraDTO compraDTO){

        return "compra-producto/add";
    }

    @PostMapping("/add")
    public String add(@Valid final CompraDTO compraDTO,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes){
        Long rowidCompra = compraService.guardar(compraDTO);


        return "redirect:/compras/add/producto/"+rowidCompra;
    }

    @GetMapping("/add/producto/{rowid}")

    public String addProducto(@PathVariable("rowid") Long rowid ,final Model model){
        CompraDTO compraDTO = compraService.getCompraDTO(rowid);

        model.addAttribute("compra",compraDTO);


        return "compra-producto/ingreso-pro";
    }











    //Metodos rest


    @GetMapping("/buscarProductoCodInterno/{codigoInterno}")
    @ResponseBody
    public ResponseEntity<ProductoDTO> buscarProductoCodigo(@PathVariable String codigoInterno){

        return ResponseEntity.ok(compraService.buscarProductoCodigo(codigoInterno));
    }

    @GetMapping("/buscarProductoCodBarras/{codBarras}")
    @ResponseBody
    public ResponseEntity<ProductoDTO> buscarProductoCodigoBarras(@PathVariable String codBarras){

        return ResponseEntity.ok(compraService.buscarProductoCodigoBarras(codBarras));
    }



}
