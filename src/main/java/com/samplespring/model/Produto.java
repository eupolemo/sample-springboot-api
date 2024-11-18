package com.samplespring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Produto {

    private Long codigo;
    @JsonProperty("tipo_vinho")
    private String tipoVinho;
    private double preco;
    private String safra;
    @JsonProperty("ano_compra")
    private int anoCompra;
}
