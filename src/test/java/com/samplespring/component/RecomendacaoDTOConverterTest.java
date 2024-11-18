package com.samplespring.component;

import com.samplespring.dto.RecomendacaoDTO;
import com.samplespring.model.Cliente;
import com.samplespring.model.Compra;
import com.samplespring.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RecomendacaoDTOConverterTest {

    @Test
    public void toRecomendacaoDTOSemComprasRetornaMensagemDefault() {
        Cliente cliente = new Cliente();
        cliente.setNome("John Doe");
        cliente.setCpf("123.456.789-00");
        cliente.setCompras(new ArrayList<>());

        RecomendacaoDTOConverter converter = new RecomendacaoDTOConverter();

        RecomendacaoDTO recomendacaoDTO = converter.toRecomendacaoDTO(cliente);

        assertEquals("John Doe", recomendacaoDTO.getNome());
        assertEquals("123.456.789-00", recomendacaoDTO.getCpf());
        assertEquals("Sem recomendação disponível", recomendacaoDTO.getRecomendacao());
    }

    @Test
    public void toRecomendacaoDTOComUmaCompraRetornaTipoVinho() {
        Produto produto = new Produto();
        produto.setTipoVinho("Tinto");

        Compra compra = new Compra();
        compra.setProduto(produto);
        compra.setQuantidade(1);

        Cliente cliente = new Cliente();
        cliente.setNome("Jane Doe");
        cliente.setCpf("987.654.321-00");
        cliente.setCompras(List.of(compra));

        RecomendacaoDTOConverter converter = new RecomendacaoDTOConverter();

        RecomendacaoDTO recomendacaoDTO = converter.toRecomendacaoDTO(cliente);

        assertEquals("Jane Doe", recomendacaoDTO.getNome());
        assertEquals("987.654.321-00", recomendacaoDTO.getCpf());
        assertEquals("Tinto", recomendacaoDTO.getRecomendacao());
    }

    @Test
    public void toRecomendacaoDTOMultiplasComprasRetornaMaisFrequenteTipoVinho() {
        Produto produto1 = new Produto();
        produto1.setTipoVinho("Tinto");

        Produto produto2 = new Produto();
        produto2.setTipoVinho("Branco");

        Compra compra1 = new Compra();
        compra1.setProduto(produto1);
        compra1.setQuantidade(3);

        Compra compra2 = new Compra();
        compra2.setProduto(produto2);
        compra2.setQuantidade(2);

        Cliente cliente = new Cliente();
        cliente.setNome("Jake Smith");
        cliente.setCpf("111.222.333-44");
        cliente.setCompras(List.of(compra1, compra2));

        RecomendacaoDTOConverter converter = new RecomendacaoDTOConverter();

        RecomendacaoDTO recomendacaoDTO = converter.toRecomendacaoDTO(cliente);

        assertEquals("Jake Smith", recomendacaoDTO.getNome());
        assertEquals("111.222.333-44", recomendacaoDTO.getCpf());
        assertEquals("Tinto", recomendacaoDTO.getRecomendacao());
    }
}