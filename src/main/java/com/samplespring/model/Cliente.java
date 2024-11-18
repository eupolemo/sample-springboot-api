package com.samplespring.model;

import lombok.Data;

import java.util.List;

@Data
public class Cliente {

    private Long codigo;
    private String nome;
    private String cpf;
    private List<Compra> compras;
}
