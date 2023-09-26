package co.epvtecnologia.sistemapos.service;

import co.epvtecnologia.sistemapos.domain.Cliente;
import co.epvtecnologia.sistemapos.domain.Producto;
import co.epvtecnologia.sistemapos.model.ClienteDTO;
import co.epvtecnologia.sistemapos.model.ProductoDTO;
import co.epvtecnologia.sistemapos.repos.ClienteRepository;
import co.epvtecnologia.sistemapos.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private  ClienteRepository clienteRepository;

    public List<ClienteDTO> findAll() {
        final List<Cliente> clientes = clienteRepository.findAll(Sort.by("idCliente"));
        return clientes.stream()
                .map(cliente -> mapToDTO(cliente, new ClienteDTO()))
                .toList();
    }

    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id){

        return clienteRepository.findByIdCliente(id);

    }

    public Cliente guardar( ClienteDTO clienteDTO){
        Cliente cliente = mapToEntity(new Cliente(),clienteDTO);

        return  clienteRepository.save(cliente);
    }


    public void update(final Long id, final ClienteDTO clienteDTO) {
        final Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(cliente, clienteDTO);
        clienteRepository.save(cliente);
    }


   private ClienteDTO mapToDTO( Cliente cliente,ClienteDTO clienteDTO){

        clienteDTO.setIdCliente(cliente.getIdCliente());
        clienteDTO.setNumDocumento(cliente.getNumDocumento());
        clienteDTO.setTipoDocumento(cliente.getTipoDocumento());
        clienteDTO.setNombres(cliente.getNombres());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setDireccion(cliente.getDireccion());
        clienteDTO.setEmail(cliente.getEmail());

        return clienteDTO;


   }

    private Cliente mapToEntity( Cliente cliente,ClienteDTO clienteDTO){

        cliente.setIdCliente(clienteDTO.getIdCliente());
        cliente.setNumDocumento(clienteDTO.getNumDocumento());
        cliente.setTipoDocumento(clienteDTO.getTipoDocumento());
        cliente.setNombres(clienteDTO.getNombres());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setEmail(clienteDTO.getEmail());

        return cliente;


    }


}
