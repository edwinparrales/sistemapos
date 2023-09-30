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
    public String list(final Model model) {

        List<CompraDTO> compraDTOList = compraService.getListCompraDTO();

        model.addAttribute("compras", compraDTOList);

        return "compra-producto/list";


    }

    @GetMapping("/add")

    public String add(@ModelAttribute("compra") CompraDTO compraDTO) {

        return "compra-producto/add";
    }

    @PostMapping("/add")
    public String add(@Valid final CompraDTO compraDTO, final Model model,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        Long rowidCompra = compraService.guardar(compraDTO);


        return "redirect:/compras/add/producto/" + rowidCompra;
    }

    @GetMapping("/add/producto/{rowid}")

    public String addProducto(@PathVariable("rowid") Long rowid, final Model model) {
        CompraDTO compraDTO = compraService.getCompraDTO(rowid);
        List<DetalleCompra> detalleCompraList = detalleCompraService.detalleCompraListByRowidCompra(compraDTO.getRowid());
        double valorTotal = detalleCompraList.stream()
                .mapToDouble(dte -> dte.getCantidad() * dte.getProducto().getValor())
                .sum();

        compraDTO.setValorTotal(valorTotal);

        compraService.update(compraDTO.getRowid(), compraDTO);

        model.addAttribute("valorTotal", valorTotal);
        model.addAttribute("detalleCompras", detalleCompraList);
        model.addAttribute("compra", compraDTO);
        DataDteCompraDTO dataDteCompraDTO = new DataDteCompraDTO();
        dataDteCompraDTO.setRowidCompra(compraDTO.getRowid());
        model.addAttribute("dataDteCompraDTO", dataDteCompraDTO);
        return "compra-producto/ingreso-pro";
    }


    @GetMapping("/eliminarDteCompra/{id}")

    public String eliminarDetalleCompra(@PathVariable Long id) {
        DetalleCompra detalleCompra = detalleCompraService.get(id);

        //Actualizar la cantidad de producto
        Producto producto = productoService.mapToEntity(productoService.get(detalleCompra.getIdProducto()), new Producto());

        producto.setCantidad(producto.getCantidad() - detalleCompra.getCantidad());
        productoService.update(producto.getId(), productoService.mapToDTO(producto, new ProductoDTO()));


        detalleCompraService.delete(id);

        return "redirect:/compras/add/producto/" + detalleCompra.getIdCompra();
    }


    //Metodos rest


    @GetMapping("/buscarProductoCodInterno/{codigoInterno}")
    @ResponseBody
    public ResponseEntity<ProductoDTO> buscarProductoCodigo(@PathVariable String codigoInterno) {

        return ResponseEntity.ok(compraService.buscarProductoCodigo(codigoInterno));
    }

    @GetMapping("/buscarProductoCodBarras/{codBarras}")
    @ResponseBody
    public ResponseEntity<ProductoDTO> buscarProductoCodigoBarras(@PathVariable String codBarras) {

        return ResponseEntity.ok(compraService.buscarProductoCodigoBarras(codBarras));
    }

    @PostMapping("/add/detalle-compra")
    public String compraAdicionarProducto(DataDteCompraDTO dataDteCompraDTO, final Model model) {
        DetalleCompra detalleCompra = new DetalleCompra();
        ProductoDTO productoDTO = productoService.get(dataDteCompraDTO.getRowidProducto());

        Producto producto = productoService.mapToEntity(productoDTO, new Producto());

        Compra compra = compraService.getCompra(dataDteCompraDTO.getRowidCompra());


        detalleCompra.setIdProducto(producto.getId());
        detalleCompra.setIdCompra(compra.getRowid());
        detalleCompra.setCantidad(Integer.parseInt(dataDteCompraDTO.getCantidad().toString()));
        detalleCompra.setCantidadAnterior(Integer.parseInt(producto.getCantidad().toString()));

        producto.setCantidad(producto.getCantidad() + dataDteCompraDTO.getCantidad());

        productoService.update(producto.getId(), productoService.mapToDTO(producto, new ProductoDTO()));


        DetalleCompra dte = detalleCompraService.guardar(detalleCompra);


        return "redirect:/compras/add/producto/" + compra.getRowid();

    }


}
