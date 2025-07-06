# 🚀 cliente-api-validacao-erros 🚀

Este repositório contém um projeto Spring Boot que exemplifica a implementação de um tratamento robusto e eficaz de erros em APIs REST. O foco principal é na **desserialização de dados** e na **validação de Value Objects (VOs)**.

O objetivo é demonstrar como transformar mensagens de erro técnicas em respostas claras e padronizadas para o cliente, ao mesmo tempo em que se mantém logs de aplicação limpos e informativos.

## ✨ Destaques do Projeto

*   **Value Objects (VOs):** Implementação de um VO para o campo `CPF`, encapsulando suas regras de validação e garantindo a integridade dos dados.
    *   *Contexto:* O conceito de Value Object foi explorado com base nos princípios de **Object Calisthenics**, uma prática incentivada pelo meu Tech Lead para aprimorar a qualidade e a manutenibilidade do código.
*   **Serialização e Desserialização com Jackson:**
    *   Utilização de `@JsonCreator` para controlar a desserialização de JSON para VO, permitindo a execução de validações no momento da criação do objeto.
    *   Utilização de `@JsonValue` para definir como o VO deve ser serializado de volta para JSON (como uma `String` simples).
*   **Tratamento Global de Exceções (`GlobalExceptionHandler`):**
    *   Configuração de um `@ControllerAdvice` para centralizar a captura e o tratamento de exceções em toda a aplicação.
    *   **Tratamento Específico de Erros de Entrada:**
        *   `HttpMessageNotReadableException` e `MismatchedInputException`: Para problemas gerais na leitura do corpo da requisição ou incompatibilidades de tipo de dados (ex: enviar um array onde se espera uma String).
        *   `IllegalArgumentException` (aninhada): Para falhas de validação de formato dentro do Value Object (ex: CPF com formato inválido).
    *   **Respostas de Erro Padronizadas:** Retorno de mensagens de erro claras e amigáveis para o cliente, seguindo um formato JSON customizado.
    *   **Logs de Aplicação Otimizados:** Demonstração de como evitar logs técnicos excessivos, mantendo-os informativos e concisos.

## 🛠️ Tecnologias Utilizadas

*   **Java 17+**
*   **Spring Boot 3.x**
*   **Spring Web**
*   **Jackson** (para Serialização/Desserialização JSON)
*   **Lombok** (para concisão de código em DTOs - opcional)


## 🧪 Como Testar a API

Utilize ferramentas como Postman, Insomnia ou `curl` para enviar requisições `POST` para o endpoint `/clientes`.

### Endpoint

`POST /clientes`

### Request Body (JSON )

#### 1. Cenário de Sucesso

```json
{
  "nome": "Ana Paula",
  "email": "ana.paula@example.com",
  "cpf": "123.456.789-00",
  "idade": 30
}
