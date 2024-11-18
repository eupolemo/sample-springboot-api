package com.samplespring.controller;

import com.samplespring.dto.ClienteDTO;
import com.samplespring.dto.CompraDTO;
import com.samplespring.dto.RecomendacaoDTO;
import com.samplespring.exception.CompraNotFoundException;
import com.samplespring.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class CompraController {

    private final CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping(value = "/compras")
    public List<CompraDTO> getCompras() {
        return compraService.getComprasOrdenadasPorValor();
    }

    @GetMapping("/maior-compra/{ano}")
    public Optional<CompraDTO> getMaiorCompraPorAno(@PathVariable int ano) {
        Optional<CompraDTO> compra = compraService.getMaiorCompraPorAno(ano);
        if (compra.isPresent()) {
            return compra;
        } else {
            throw new CompraNotFoundException("Maior compra por ano não encontrada");
        }
    }

    @GetMapping("/clientes-fieis")
    public List<ClienteDTO> getTop3ClientesFieis() {
        return compraService.getTop3ClientesFieis();
    }

    @GetMapping("/recomendacao/cliente/tipo")
    public List<RecomendacaoDTO> getRecomendacaoVinho() {
        return compraService.getRecomendacaoVinho();
    }
}
