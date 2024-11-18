package com.samplespring.component;

import com.samplespring.dto.RecomendacaoDTO;
import com.samplespring.model.Cliente;
import com.samplespring.model.Compra;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RecomendacaoDTOConverter {
    public RecomendacaoDTO toRecomendacaoDTO(Cliente cliente) {
        String recomendacaoVinho = calcularRecomendacaoVinho(cliente);
        return new RecomendacaoDTO(cliente.getNome(), cliente.getCpf(), recomendacaoVinho);
    }

    private String calcularRecomendacaoVinho(Cliente cliente) {
        Map<String, Integer> tipoVinhoCount = cliente.getCompras().stream()
                .collect(Collectors.groupingBy(
                        compra -> compra.getProduto()
                                .getTipoVinho(), Collectors.summingInt(Compra::getQuantidade)
                ));
        return tipoVinhoCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Sem recomendação disponível");
    }

    public List<RecomendacaoDTO> toRecomendacaoDTOList(List<Cliente> clientes) {
        return clientes.stream().map(this::toRecomendacaoDTO).collect(Collectors.toList());
    }
}

