package com.exercicio.validacao.cliente_api.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class CPF {
    private final String valor;

    public CPF(String valor) {
        this.valor = valor;
    }

    //Para Desserialização (JSON -> Objeto Java)
    @JsonCreator
    public static CPF of(String valor){
        if(valor == null || valor.trim().isEmpty()){
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }

        if(!valor.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")){
            throw new IllegalArgumentException("Formato de CPF inválido, Use XXX.XXX.XXX-XX");
        }

        return new CPF(valor);
    }

    //Para Serialização (Objeto Java -> JSON)
    @JsonValue
    public String getValor(){
        return valor;
    }

    @Override
    public String toString(){
        return valor;
    }
}

