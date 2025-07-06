package com.exercicio.validacao.cliente_api.exception;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class );

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErroResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){

        ApiErroResponse apiErroResponse = new ApiErroResponse();
        ApiErroResponse.ApiErro apiErro = new ApiErroResponse.ApiErro();
        List<String> subErros = new ArrayList<>();

        apiErro.setTimestamp(LocalDateTime.now().toString());
        apiErro.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
        apiErro.setCodigoErro(HttpStatus.UNPROCESSABLE_ENTITY.value());
        apiErro.setMensagem("Erro de formato de dados");

        Throwable causa = exception.getCause();

        if(causa instanceof MismatchedInputException) {
            MismatchedInputException mismatchedInputException = (MismatchedInputException) causa;

            String nomeCampo = mismatchedInputException.getPath().stream()
                    .filter(p -> p.getFieldName() != null)
                    .map(p -> "'" + p.getFieldName() + "'")
                    .collect(Collectors.joining("."));

            String tipoRecebido = "desconhecido";
            if (mismatchedInputException.getMessage().contains("from ")) {
                String[] parts = mismatchedInputException.getMessage().split("from ");
                if (parts.length > 1) {
                    tipoRecebido = parts[1].split(" ")[0];
                }
            }

            String mensagemDetalhada = String.format("O valor fornecido para %s não está no formato esperado. Esperado: %s, Recebido: %s",
                    nomeCampo.isEmpty() ? "um campo" : nomeCampo,
                    mismatchedInputException.getTargetType() != null ? mismatchedInputException.getTargetType().getSimpleName() : "String",
                    tipoRecebido);

            apiErro.setMensagem("Os dados enviados não estão no formato esperado");
            apiErro.setMensagemDetalhada(mensagemDetalhada);
            subErros.add("Erro de desserialização de JSON");

            log.warn("Erro de desserialização de JSON para o campo {}: {}", nomeCampo, mensagemDetalhada);
        } else{
            apiErro.setMensagem("Erro na leitura do corpo da requisição");
            apiErro.setMensagemDetalhada(apiErro.getMensagemDetalhada());
            subErros.add("Erro no formato de algum campo passado no body da requisição");
            log.error("Erro na leitura da requisição HTTP: {}", exception.getMessage());
        }

        apiErro.setSubErros(subErros);
        apiErro.setMensagemDetalhada(apiErro.getMensagemDetalhada());
        apiErroResponse.setApierro(apiErro);

        return new ResponseEntity<>(apiErroResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErroResponse> handleIllegalArgumentException(IllegalArgumentException exception){

        ApiErroResponse apiErroResponse = new ApiErroResponse();
        ApiErroResponse.ApiErro apiErro = new ApiErroResponse.ApiErro();
        List<String> subErros = new ArrayList<>();

        apiErro.setTimestamp(LocalDateTime.now().toString());
        apiErro.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
        apiErro.setCodigoErro(HttpStatus.UNPROCESSABLE_ENTITY.value());
        apiErro.setMensagem("Erro de validação de dados");
        apiErro.setMensagemDetalhada(exception.getMessage());

        log.warn("Erro de validação de Value Object: {}", exception.getMessage(), exception);

        apiErroResponse.setApierro(apiErro);

        return new ResponseEntity<>(apiErroResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
