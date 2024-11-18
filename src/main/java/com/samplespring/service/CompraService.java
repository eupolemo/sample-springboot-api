package com.samplespring.service;

import com.samplespring.component.ClienteDTOConverter;
import com.samplespring.component.CompraDTOConverter;
import com.samplespring.component.RecomendacaoDTOConverter;
import com.samplespring.dto.ClienteDTO;
import com.samplespring.dto.CompraDTO;
import com.samplespring.dto.RecomendacaoDTO;
import com.samplespring.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompraService {

    private final RecomendacaoDTOConverter recomendacaoDTOConverter;

    private final ClienteDTOConverter clienteDTOConverter;

    private final CompraDTOConverter compraDTOConverter;

    private final ClienteService clienteService;

    @Autowired
    public CompraService(RecomendacaoDTOConverter recomendacaoDTOConverter,
                         ClienteDTOConverter clienteDTOConverter,
                         CompraDTOConverter compraDTOConverter,
                         ClienteService clienteService) {
        this.recomendacaoDTOConverter = recomendacaoDTOConverter;
        this.clienteDTOConverter = clienteDTOConverter;
        this.compraDTOConverter = compraDTOConverter;
        this.clienteService = clienteService;
    }

    public List<CompraDTO> getComprasOrdenadasPorValor() {
        List<CompraDTO> comprasOrdenadasPorValor = compraDTOConverter.toCompraDTOList(clienteService.getClientes());
        comprasOrdenadasPorValor.sort(Comparator.comparingDouble(CompraDTO::getValorTotal));
        return comprasOrdenadasPorValor;
    }

    public Optional<CompraDTO> getMaiorCompraPorAno(int ano) {
        List<CompraDTO> compras = compraDTOConverter.toCompraDTOList(clienteService.getClientes());
        return compras.stream()
                .filter(compraDTO -> compraDTO.getAnoCompra() == ano)
                .max(Comparator.comparingDouble(CompraDTO::getValorTotal));
    }

    public List<ClienteDTO> getTop3ClientesFieis() {
        List<Cliente> clientes = clienteService.getClientes();

        List<Cliente> clientesFieis = clientes.stream()
                .sorted(Comparator.comparingDouble
                        (this::calcularFidelidadeCliente).reversed())
                .limit(3)
                .collect(Collectors.toList());

        return clienteDTOConverter.toClienteDTOList(clientesFieis);
    }

    public List<RecomendacaoDTO> getRecomendacaoVinho() {
        List<Cliente> clientes = clienteService.getClientes();
        return recomendacaoDTOConverter.toRecomendacaoDTOList(clientes);
    }

    protected double calcularFidelidadeCliente(Cliente cliente) {
        double numeroDeCompras = cliente.getCompras().size();
        double valorTotal = cliente.getCompras().stream()
                .mapToDouble(compra -> compra.getProduto().getPreco() * compra.getQuantidade())
                .sum();
        return numeroDeCompras * valorTotal;
    }
}
