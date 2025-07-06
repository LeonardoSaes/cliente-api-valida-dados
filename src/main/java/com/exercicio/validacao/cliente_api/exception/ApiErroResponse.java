package com.exercicio.validacao.cliente_api.exception;

import java.util.List;

public class ApiErroResponse {

    private ApiErro apierro;

    public ApiErro getApierro() {
        return apierro;
    }

    public void setApierro(ApiErro apierro) {
        this.apierro = apierro;
    }

    public static class ApiErro {
        private String timestamp;
        private String status;
        private Integer codigoErro;
        private String mensagem;
        private String mensagemDetalhada;
        private List<String> subErros;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getCodigoErro() {
            return codigoErro;
        }

        public void setCodigoErro(Integer codigoErro) {
            this.codigoErro = codigoErro;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagemDetalhada() {
            return mensagemDetalhada;
        }

        public void setMensagemDetalhada(String mensagemDetalhada) {
            this.mensagemDetalhada = mensagemDetalhada;
        }

        public List<String> getSubErros() {
            return subErros;
        }

        public void setSubErros(List<String> subErros) {
            this.subErros = subErros;
        }
    }
}
