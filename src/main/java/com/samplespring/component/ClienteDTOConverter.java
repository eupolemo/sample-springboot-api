package com.samplespring.component;

import com.samplespring.dto.ClienteDTO;
import com.samplespring.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteDTOConverter {

    public ClienteDTO toClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCpf(cliente.getCpf());
        return clienteDTO;
    }

    public List<ClienteDTO> toClienteDTOList(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toClienteDTO)
                .collect(Collectors.toList());
    }
}
