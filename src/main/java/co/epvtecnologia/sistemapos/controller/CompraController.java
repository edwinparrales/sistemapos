package co.epvtecnologia.sistemapos.controller;


import co.epvtecnologia.sistemapos.domain.Compra;
import co.epvtecnologia.sistemapos.domain.DetalleCompra;
import co.epvtecnologia.sistemapos.domain.Producto;
import co.epvtecnologia.sistemapos.model.CompraDTO;
import co.epvtecnologia.sistemapos.model.DataDteCompraDTO;
import co.epvtecnologia.sistemapos.model.ProductoDTO;
import co.epvtecnologia.sistemapos.service.CompraService;
import co.epvtecnologia.sistemapos.service.DetalleCompraService;
import co.epvtecnologia.sistemapos.service.ProductoService;
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
    @Autowired
     private ProductoService productoService;
    @Autowired
    private DetalleCompraService detalleCompraService;

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

    public String addProducto(@PathVariable("rowid") Long rowid , final Model model){
        CompraDTO compraDTO = compraService.getCompraDTO(rowid);
        //List<DetalleCompra> detalleCompraList = compraService.getDetalleCompra(compraDTO.getRowid());
        //model.addAttribute("detalleCompras",detalleCompraList);
        model.addAttribute("compra",compraDTO);

        return "compra-producto/ingreso-pro";
    }


    @PostMapping("/add/detalle-compra")
    @ResponseBody
    public ResponseEntity addDetalleCompra (@RequestBody DataDteCompraDTO dataDteCompraDTO){
        DetalleCompra detalleCompra = new DetalleCompra();
        ProductoDTO productoDTO = productoService.get(dataDteCompraDTO.getRowidProducto());

        Producto producto = productoService.mapToEntity(productoDTO,new Producto());

        Compra compra=compraService.getCompra(dataDteCompraDTO.getRowidCompra());


        detalleCompra.setProducto(producto);
        detalleCompra.setCompra(compra);
        detalleCompra.setCantidad(   Integer.parseInt(dataDteCompraDTO.getCantidad().toString()));
        detalleCompra.setCantidadAnterior(Integer.parseInt(producto.getCantidad().toString()));

        producto.setCantidad(producto.getCantidad()+ dataDteCompraDTO.getCantidad());

        productoService.update(producto.getId(),productoService.mapToDTO(producto,new ProductoDTO()));


       DetalleCompra dte = detalleCompraService.guardar(detalleCompra);

        return ResponseEntity.ok("Realizaep");
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
