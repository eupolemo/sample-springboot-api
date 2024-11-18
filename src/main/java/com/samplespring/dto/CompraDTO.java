package com.samplespring.dto;

import lombok.Data;

@Data
public class CompraDTO {

    private String nome;
    private String cpf;
    private int quantidade;
    private String tipoVinho;
    private double preco;
    private String safra;
    private int anoCompra;
    private double valorTotal;
}
