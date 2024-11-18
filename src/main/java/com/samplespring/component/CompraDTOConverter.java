package com.samplespring.component;

import com.samplespring.dto.CompraDTO;
import com.samplespring.model.Cliente;
import com.samplespring.model.Compra;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class CompraDTOConverter {
    public CompraDTO toCompraDTO(Compra compra, String nome, String cpf) {
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setNome(nome);
        compraDTO.setCpf(cpf);
        if (compra.getProduto() != null) {
            compraDTO.setAnoCompra(compra.getProduto().getAnoCompra());
            compraDTO.setPreco(compra.getProduto().getPreco());
            compraDTO.setSafra(compra.getProduto().getSafra());
            compraDTO.setTipoVinho(compra.getProduto().getTipoVinho());
            compraDTO.setQuantidade(compra.getQuantidade());

            BigDecimal preco = BigDecimal.valueOf(compra.getProduto().getPreco()).setScale(
                    2, RoundingMode.HALF_UP);
            compraDTO.setValorTotal(preco.multiply(BigDecimal.valueOf(compra.getQuantidade())).doubleValue());
        }
        return compraDTO;
    }

    public List<CompraDTO> toCompraDTOList(Cliente cliente) {
        List<CompraDTO> comprasDTO = new ArrayList<>();
        cliente.getCompras().forEach(compra -> {
            comprasDTO.add(toCompraDTO(compra, cliente.getNome(), cliente.getCpf()));
        });
        return comprasDTO;
    }

    public List<CompraDTO> toCompraDTOList(List<Cliente> clientes) {
        List<CompraDTO> comprasDTO = new ArrayList<>();

        for (Cliente cliente : clientes) {
            comprasDTO.addAll(toCompraDTOList(cliente));
        }

        return comprasDTO;
    }
}
