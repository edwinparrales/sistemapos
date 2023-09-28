package co.epvtecnologia.sistemapos.rest;

import co.epvtecnologia.sistemapos.domain.DetalleCompra;
import co.epvtecnologia.sistemapos.service.DetalleCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/detallecompra")
public class DetalleCompraRest {
    @Autowired
    private DetalleCompraService detalleCompraService;

    @GetMapping("/listar")

    public ResponseEntity<List<DetalleCompra>> list(){
        List<DetalleCompra> detalleCompraList = detalleCompraService.listaDetalleCompra();

        return new ResponseEntity<>(detalleCompraList, HttpStatus.OK);
    }


}
