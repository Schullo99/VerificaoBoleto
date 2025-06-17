# Validador de Boletos

## Descrição

O **Validador de Boletos** é um sistema desenvolvido em Java com Spring Boot, projetado para validar boletos de pagamento a fim de combater fraudes. A aplicação recebe os dados de um boleto, realiza uma série de verificações de consistência e, caso o boleto seja válido, o armazena em um banco de dados. Tentativas de pagamento com dados que conflitam com registros existentes são salvas como fraude.

O projeto conta com uma API REST completa para o gerenciamento de boletos, fraudes, clientes e empresas, além de uma interface web simples para a validação.

## Tecnologias Utilizadas

* **Backend:**
    * Java 24
    * Spring Boot 3.4.5
    * Spring Data JPA
    * Spring Web
* **Banco de Dados:**
    * MySQL
* **Build:**
    * Maven

## Funcionalidades

* Validação de boletos com base na linha digitável, código do banco, CPF, data de vencimento e valor.
* Persistência de boletos válidos e de tentativas de fraude no banco de dados.
* API REST para operações CRUD (Create, Read, Update, Delete) das entidades `Boleto`, `Fraude`, `Cliente` e `Empresa`.
* Interface web para interação do usuário com o sistema de validação.

## Como Executar o Projeto

### Pré-requisitos

* JDK 24 ou superior.
* Maven 3.x.
* Uma instância do MySQL em execução.

### 1. Configuração do Banco de Dados

Antes de iniciar a aplicação, você precisa configurar o banco de dados.

1.  Acesse seu cliente MySQL e crie um novo banco de dados:
    ```sql
    CREATE DATABASE projectvalidacaoboleto;
    ```
2.  O projeto está configurado para que o Hibernate atualize o schema automaticamente (`ddl-auto=update`). As tabelas serão criadas na primeira inicialização.
3.  Verifique se as credenciais no arquivo `src/main/resources/application.properties` correspondem às do seu ambiente MySQL. As credenciais padrão são:
    * **URL:** `jdbc:mysql://localhost:3306/projectvalidacaoboleto`
    * **Usuário:** `root`
    * **Senha:** `$uperBlastoiseAgiota#15`

### 2. Iniciando a Aplicação

1.  Clone o repositório para a sua máquina local.
2.  Abra um terminal na pasta raiz do projeto.
3.  Execute o seguinte comando Maven para iniciar a aplicação:
    ```bash
    mvn spring-boot:run
    ```
4.  Após a inicialização, a aplicação estará disponível em `http://localhost:8080`.

## Como Utilizar o Verificador de Boletos

Siga os passos abaixo para validar um boleto na interface web:

1.  Acesse a aplicação no seu navegador: `http://localhost:8080`.
2.  Tenha em mãos um boleto para realizar o teste.
3.  **Linha Digitável:** No primeiro campo, insira a linha digitável completa do boleto (44 caracteres), sem pontos ou espaços.
4.  **Código do Banco:** Informe o código do banco, que corresponde aos três primeiros dígitos da linha digitável.
5.  **Seu CPF:** Digite o seu CPF, utilizando apenas os 11 números.
6.  **Data de Vencimento:** Selecione a data de vencimento do boleto no calendário.
7.  **Valor (R$):** Informe o valor do boleto.
8.  Clique no botão **“Validar Boleto”** para realizar a verificação. O resultado aparecerá logo abaixo.
9.  Para limpar todos os campos e começar novamente, clique em **“Limpar Campos”**.

## API Endpoints

A aplicação expõe os seguintes endpoints REST para gerenciamento:

### Boleto
* `POST /boleto/verificar`: Valida um boleto e o salva se for válido.

### Fraudes
* `GET /fraude`: Lista todas as fraudes.
* `GET /fraude/{id}`: Busca uma fraude por ID.
* `GET /fraude/cpf/{cpf}`: Busca uma fraude por CPF.
* `GET /fraude/linha/{linhaDigitavel}`: Busca uma fraude por linha digitável.
* `POST /fraude`: Cadastra uma nova fraude.
* `PUT /fraude/{id}`: Atualiza uma fraude existente.
* `DELETE /fraude/{id}`: Deleta uma fraude.

### Clientes
* `GET /clientes`: Lista todos os clientes.
* `GET /cliente/{id}`: Busca um cliente por ID.
* `POST /clientes`: Cadastra um novo cliente.
* `PUT /clientes/{id}`: Atualiza um cliente.
* `DELETE /clientes/{id}`: Deleta um cliente.

### Empresas
* `GET /Empresa`: Lista todas as empresas.
* `GET /Empresa/{id}`: Busca uma empresa por ID.
* `POST /Empresa`: Cadastra uma nova empresa.
* `PUT /Empresa/{id}`: Atualiza uma empresa.
* `DELETE /Empresa/{id}`: Deleta uma empresa.
