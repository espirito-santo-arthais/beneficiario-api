# API de Beneficiário

## Descrição

Esta aplicação é uma API REST desenvolvida em Java utilizando Spring Boot para gerenciar o cadastro de beneficiários de um plano de saúde. A API permite operações de CRUD (Create, Read, Update, Delete) em beneficiários e seus documentos.

## Funcionalidades

- **Cadastrar um beneficiário junto com seus documentos**
- **Listar todos os beneficiários cadastrados**
- **Listar todos os documentos de um beneficiário a partir de seu id**
- **Atualizar os dados cadastrais de um beneficiário**
- **Remover um beneficiário**

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.2.5
- Banco de Dados H2 (em memória)
- Spring Data JPA
- Spring Security
- Swagger/OpenAPI para documentação
- Maven para gerenciamento de dependências

## Endpoints

### Cadastrar um Beneficiário

- **POST /beneficiarios**
- Request Body: JSON representando o beneficiário e seus documentos.

### Listar Todos os Beneficiários

- **GET /beneficiarios**

### Listar Documentos de um Beneficiário

- **GET /beneficiarios/{id}/documentos**

### Atualizar Dados Cadastrais de um Beneficiário

- **PUT /beneficiarios/{id}**
- Request Body: JSON com os dados atualizados do beneficiário.

### Remover um Beneficiário

- **DELETE /beneficiarios/{id}**

## Autenticação e Autorização

A aplicação utiliza autenticação baseada em token. O token deve ser passado no header da requisição como `x-auth-secret-key`.

## Documentação da API

A documentação dos endpoints está disponível através do Swagger UI. A interface pode ser acessada em:

[http://localhost:8080/beneficiario-api/v1/swagger-ui.html](http://localhost:8080/beneficiario-api/v1/swagger-ui.html)

## Executando o Projeto

### Pré-requisitos

- Java 21
- Maven

### Passos para Executar

1. **Clone o repositório:**

    ```bash
    git clone https://github.com/seu-usuario/beneficiario-api.git
    cd beneficiario-api
    ```

2. **Compile o projeto:**

    ```bash
    mvn clean install
    ```

3. **Execute a aplicação:**

    ```bash
    mvn spring-boot:run
    ```

A aplicação estará disponível em [http://localhost:8080/beneficiario-api/v1](http://localhost:8080/beneficiario-api/v1).

### Observação

Certifique-se de definir as variáveis de ambiente `TOKEN_KEY` e `TOKEN_VALUE` conforme necessário para autenticação.

## Contato

Para mais informações ou questões, entre em contato com a equipe:

- **Nome:** Equipe Ekan
- **Email:** contato@ekan.com.br
- **URL:** [https://ekan.com.br/contato.html](https://ekan.com.br/contato.html)

---

Desenvolvido por Ekan Systems. Todos os direitos reservados.
