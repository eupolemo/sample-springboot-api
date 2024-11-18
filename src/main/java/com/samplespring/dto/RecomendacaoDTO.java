package com.samplespring.dto;

import lombok.Data;

@Data
public class RecomendacaoDTO {

    private String nome;
    private String cpf;
    private String recomendacao;

    public RecomendacaoDTO(String nome, String cpf, String recomendacao) {
        this.nome = nome;
        this.cpf = cpf;
        this.recomendacao = recomendacao;
    }
}
