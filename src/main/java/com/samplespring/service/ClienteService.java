package com.samplespring.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samplespring.model.Cliente;
import com.samplespring.model.Produto;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ClienteService {
    public List<Cliente> getClientes() {
        List<Cliente> clientes;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ClassPathResource clientesResource = new ClassPathResource("data/clientes.json");

            clientes = objectMapper.readValue(
                    clientesResource.getFile(), new TypeReference<>() {
                    });

            ClassPathResource produtosResource = new ClassPathResource("data/produtos.json");

            List<Produto> produtos = objectMapper.readValue(
                    produtosResource.getFile(), new TypeReference<>() {
                    });

            clientes.forEach(cliente -> cliente.getCompras().forEach(compra -> {
                compra.setProduto(produtos.stream().filter(
                        produto -> produto.getCodigo().equals(
                                compra.getCodigo())).findFirst().orElse(null));
            }));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }
}
