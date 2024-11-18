package com.samplespring.model;

import lombok.Data;

@Data
public class Compra {

    private Long codigo;
    private int quantidade;
    private Produto produto;
}
