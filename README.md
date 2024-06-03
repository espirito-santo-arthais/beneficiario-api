# API de Beneficiário

## Descrição

Esta aplicação é uma API REST desenvolvida em Java utilizando Spring Boot para gerenciar o cadastro de beneficiários de um plano de saúde. A API permite operações de CRUD (Create, Read, Update, Delete) em beneficiários. Já os documentos são apenas criados no endpoint de criação do beneficiário.

As demais operações de CRUD dos documentos não foram solicitadas nesta primeira demanda e provavelmente serão objeto de um outro ciclo de desenvolvimento.

Também não foram solicitados testes unitários, e2e ou outros tipos de testes, que acreditamos ficariam também para outros cíclos de desenvolvimento.o
## Funcionalidades
- **Cadastrar um beneficiário junto com seus documentos**
- **Listar todos os beneficiários cadastrados**
- **Listar todos os documentos de um beneficiário a partir de seu id**
- **Atualizar os dados cadastrais de um beneficiário**
- **Remover um beneficiário**

## Tecnologias Utilizadas
- Java 21- Spring Boot 3.2.5- Banco de Dados H2 (em memória)- Spring Data JPA- Spring Security- Swagger/OpenAPI para documentação- Maven para gerenciamento de dependências

## Arquitetura do Software
A aplicação segue uma arquitetura em camadas, dividida em três camadas principais: `resources`, `domain` e `infrastructure`. Essa abordagem adota o padrão de projeto **Arquitetura Hexagonal** (também conhecido como **Arquitetura Ports and Adapters**), que visa isolar a lógica de negócios das outras partes do sistema.

### 1. Camada Resources
- **Responsabilidade:** Esta é a camada mais externa da aplicação e é responsável por definir os endpoints da API REST.

- **Função:** Recebe as requisições HTTP dos clientes, valida os dados e encaminha as chamadas para a camada de domínio.

- **Transformação:** Utiliza mapeadores (mappers) para converter os dados recebidos em objetos de domínio apropriados.

### 2. Camada Domain
- **Responsabilidade:** Centraliza a lógica de negócios da aplicação.

- **Função:** Implementa as regras de negócios, validações específicas e processos centrais da aplicação.

- **Isolamento:** A camada de domínio é independente das outras camadas, garantindo que as regras de negócios não sejam afetadas por detalhes de infraestrutura ou de apresentação.

### 3. Camada Infrastructure

- **Responsabilidade:** Gerencia a persistência dos dados no banco de dados, utilizando JPA.

- **Função:** Realiza as operações de CRUD e interações diretas com o banco de dados.

- **Transformação:** Utiliza mapeadores (mappers) para converter os objetos de domínio em entidades persistentes e vice-versa.

Essa estrutura em camadas facilita a manutenção, teste e evolução do sistema, permitindo que mudanças em uma camada não afetem as outras.
## Banco de Dados
A aplicação utiliza o banco de dados H2, que é um banco de dados em memória e embarcado. Isso significa que os dados são armazenados apenas na memória enquanto a aplicação está em execução e são perdidos quando a aplicação é parada. Portanto, a cada novo start da aplicação, é necessário reincluir os registros.
### Configuração do Banco de Dados
O banco de dados H2 é configurado automaticamente pela aplicação Spring Boot com os seguintes parâmetros:
- **URL:** `jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL`- **Driver:** `org.h2.Driver`- **Usuário:** `sa`- **Senha:** (vazia)- **Dialeto:** `org.hibernate.dialect.H2Dialect`
### Massa de Dados para Teste Funcional
Para facilitar os testes funcionais, aqui está uma massa de dados que pode ser utilizada para incluir registros na aplicação:

```json
{
  "name": "João da Silva",
  "phoneNumber": "+5511987654321",
  "birthDate": "1980-03-15",
  "documentList": [
    {
      "documentType": "CPF",
      "description": "12345678901"
    },
    {
      "documentType": "RG",
      "description": "12.345.678-9 SSP RJ"
    }
  ]
}

{
  "name": "Maria Oliveira",
  "phoneNumber": "+5511998765432",
  "birthDate": "1990-07-20",
  "documentList": [
    {
      "documentType": "CPF",
      "description": "98765432100"
    },
    {
      "documentType": "RG",
      "description": "98.765.432-1 SSP SP"
    }
  ]
}

{
  "name": "Carlos Pereira",
  "phoneNumber": "+5511996655443",
  "birthDate": "1975-12-05",
  "documentList": [
    {
      "documentType": "CPF",
      "description": "56789012345"
    },
    {
      "documentType": "RG",
      "description": "56.789.012-3 SSP MG"
    }
  ]
}

{
  "name": "Ana Souza",
  "phoneNumber": "+5511988877665",
  "birthDate": "1985-04-10",
  "documentList": [
    {
      "documentType": "CPF",
      "description": "23456789012"
    },
    {
      "documentType": "RG",
      "description": "23.456.789-0 SSP SP"
    }
  ]
}

{
  "name": "Pedro Costa",
  "phoneNumber": "+5511975544332",
  "birthDate": "1995-11-30",
  "documentList": [
    {
      "documentType": "CPF",
      "description": "67890123456"
    },
    {
      "documentType": "RG",
      "description": "67.890.123-4 SSP RJ"
    }
  ]
}
```

Para incluir esses registros, envie requisições POST para o endpoint /beneficiarios com o corpo da requisição contendo os dados no formato acima.

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
    
    git clone https://github.com/seu-usuario/beneficiario-api.git
    cd beneficiario-api

2. **Compile o projeto:**

    mvn clean install

3. **Execute a aplicação:**

    mvn spring-boot:run

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
