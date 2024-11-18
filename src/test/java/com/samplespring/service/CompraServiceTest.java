package com.samplespring.service;

import com.samplespring.component.ClienteDTOConverter;
import com.samplespring.component.CompraDTOConverter;
import com.samplespring.component.RecomendacaoDTOConverter;
import com.samplespring.model.Cliente;
import com.samplespring.model.Compra;
import com.samplespring.model.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CompraServiceTest {

    @Mock
    private RecomendacaoDTOConverter recomendacaoDTOConverter;

    @Mock
    private ClienteDTOConverter clienteDTOConverter;

    @Mock
    private CompraDTOConverter compraDTOConverter;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private CompraService compraService;

    @Test
    void testCalcularFidelidadeClienteSemCompras() {
        Cliente cliente = new Cliente();
        cliente.setCompras(Collections.emptyList());

        double fidelidade = compraService.calcularFidelidadeCliente(cliente);

        assertEquals(0, fidelidade);
    }

    @Test
    void testCalcularFidelidadeClienteComUmaCompra() {
        Cliente cliente = new Cliente();
        Compra compra = new Compra();
        Produto produto = new Produto();
        produto.setPreco(10.0);
        compra.setProduto(produto);
        compra.setQuantidade(2);
        cliente.setCompras(Collections.singletonList(compra));

        double expectedFidelidade = 1 * (10.0 * 2);

        double fidelidade = compraService.calcularFidelidadeCliente(cliente);

        assertEquals(expectedFidelidade, fidelidade);
    }

    @Test
    void testCalcularFidelidadeClienteComMultiplasCompras() {
        Cliente cliente = new Cliente();
        Produto produto1 = new Produto();
        produto1.setPreco(10.0);
        Compra compra1 = new Compra();
        compra1.setProduto(produto1);
        compra1.setQuantidade(2);

        Produto produto2 = new Produto();
        produto2.setPreco(20.0);
        Compra compra2 = new Compra();
        compra2.setProduto(produto2);
        compra2.setQuantidade(1);

        cliente.setCompras(Arrays.asList(compra1, compra2));

        double expectedFidelidade = 2 * ((10.0 * 2) + (20.0));

        double fidelidade = compraService.calcularFidelidadeCliente(cliente);

        assertEquals(expectedFidelidade, fidelidade);
    }
}