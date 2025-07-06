package com.exercicio.validacao.cliente_api.dto;

import com.exercicio.validacao.cliente_api.vo.CPF;

public record ClienteRequest(String nome, String email, CPF cpf, Integer idade) {
}
