package co.epvtecnologia.sistemapos.controller;

import co.epvtecnologia.sistemapos.domain.Cliente;
import co.epvtecnologia.sistemapos.model.ClienteDTO;
import co.epvtecnologia.sistemapos.model.ProductoDTO;
import co.epvtecnologia.sistemapos.service.ClienteService;
import co.epvtecnologia.sistemapos.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String lista(final Model model) {
        List<ClienteDTO> listaClientes = clienteService.findAll();
        model.addAttribute("listaClientes", listaClientes);
        return "cliente/list";
    }

    @GetMapping("/add")
    public String registrarCliente(@ModelAttribute("cliente") ClienteDTO cliente) {

        return "cliente/add";
    }

    @PostMapping("/add")
    public String registrarCliente(@ModelAttribute("cliente") @Valid ClienteDTO cliente, final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {


        if (!bindingResult.hasFieldErrors("numDocumento") && clienteService.numeroDocumentoExists(cliente.getNumDocumento())) {
            bindingResult.rejectValue("numDocumento", "10000");
        }

        if (bindingResult.hasErrors()) {
            return "cliente/add";
        }


        clienteService.guardar(cliente);


        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, "Cliente Creado");
        return "redirect:/clientes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") final Long id, final Model model) {
        model.addAttribute("cliente", clienteService.findById(id));
        return "cliente/editar";
    }


    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
                       @ModelAttribute("cliente") @Valid final ClienteDTO clienteDTO,
                       final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasFieldErrors("numDocumento") && clienteService.numeroDocumentoExists(clienteDTO.getNumDocumento())) {
            bindingResult.rejectValue("numDocumento", "10000");
        }


        if (bindingResult.hasErrors()) {
            return "cliente/editar";
        }
        clienteService.update(id, clienteDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, "Cliente Actualizado");
        return "redirect:/clientes";


    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        clienteService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("producto.delete.success"));
        return "redirect:/clientes";
    }


}
