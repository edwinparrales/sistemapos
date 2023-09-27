package co.epvtecnologia.sistemapos.controller;

import co.epvtecnologia.sistemapos.domain.IngresoProducto;
import co.epvtecnologia.sistemapos.model.DetalleIngresoProductoDTO;
import co.epvtecnologia.sistemapos.model.IngresoProductoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ingreso-productos")
public class IngresoProductoController
{
    private DetalleIngresoProductoDTO detalleIngresoProductoDTO;
    private List<DetalleIngresoProductoDTO> listDetalleIngresoProductoDTOS;
    @GetMapping()
    public String inicio(final Model model){

        return "ingreso-producto/ingresoInventario";
    }

    private void agregarRegistroDto(DetalleIngresoProductoDTO detalleIngresoProductoDTO){

        listDetalleIngresoProductoDTOS.add(detalleIngresoProductoDTO);


    }


}
