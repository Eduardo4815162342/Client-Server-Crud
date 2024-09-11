
# Sistema de Cadastro de Usuários com Persistência em JSON, ViaCEP e Comunicação por Sockets

Este projeto é uma aplicação simples de CRUD (Criar, Ler, Atualizar, Deletar) para o cadastro de usuários. A persistência dos dados é feita em um arquivo JSON, e o sistema se comunica com a API ViaCEP para preencher os dados de endereço dos usuários. A comunicação entre o cliente e o servidor é feita por meio de Sockets.

## Funcionalidades

1. **CRUD de Usuários**: Permite cadastrar, listar, atualizar e remover usuários, persistindo os dados em um arquivo `usuarios.json`.
2. **Busca de Endereço via API ViaCEP**: Ao cadastrar ou atualizar um usuário, o sistema faz uma requisição para a API ViaCEP para obter as informações de endereço baseadas no CEP fornecido.
3. **Comunicação Cliente-Servidor via Sockets**: O cliente interage com o servidor, que realiza as operações e retorna as respostas.
4. **Execução contínua no cliente**: O cliente agora permanece em execução até que o usuário escolha a opção "Sair", permitindo múltiplas operações sem necessidade de reiniciar.

## Requisitos

- Compatível com Java 22
- Biblioteca Gson para manipulação de JSON
- Acesso à internet para realizar requisições à API ViaCEP

## Estrutura do Projeto

- `Usuario.java`: Classe que representa o modelo de dados do usuário.
- `UsuarioService.java`: Classe responsável pelas operações CRUD e pela manipulação do arquivo JSON.
- `Servidor.java`: Classe que contém a lógica do servidor, responsável por receber as requisições dos clientes e realizar as operações solicitadas.
- `Cliente.java`: Classe que interage com o usuário, enviando comandos para o servidor por meio de Sockets.

## Como Executar o Projeto

### 1. Clonar o Repositório

Clone o repositório ou copie os arquivos necessários para o seu ambiente local.

```bash
git clone <url-do-repositorio>
```

### 2. Compilar e Executar o Servidor

1. Compile o código do servidor:

```bash
javac Servidor.java Usuario.java UsuarioService.java
```

2. Execute o servidor:

```bash
java Servidor
```

O servidor será iniciado e ficará aguardando as requisições do cliente.

### 3. Compilar e Executar o Cliente

1. Compile o código do cliente:

```bash
javac Cliente.java
```

2. Execute o cliente:

```bash
java Cliente
```

O cliente permitirá que você interaja com o sistema escolhendo as operações CRUD disponíveis. Agora, o cliente continuará rodando até que o usuário escolha a opção "SAIR".

### 4. Operações Disponíveis

No cliente, você pode escolher entre as seguintes opções:

- **LISTAR**: Lista todos os usuários cadastrados.
- **CADASTRAR**: Permite cadastrar um novo usuário, solicitando nome, CPF, e-mail e CEP. O endereço será preenchido automaticamente com base no CEP informado.
- **ATUALIZAR**: Atualiza os dados de um usuário com base no CPF.
- **REMOVER**: Remove um usuário com base no CPF.
- **SAIR**: Encerra a execução do cliente.

### Exemplo de Interação

1. Ao iniciar o cliente, escolha a operação desejada. Exemplo:

```bash
Escolha uma operação: LISTAR, CADASTRAR, ATUALIZAR, REMOVER, SAIR
```

2. Se você escolher **CADASTRAR**, o sistema solicitará os dados do usuário e o CEP para preencher o endereço automaticamente.

```bash
Nome:
CPF:
Email:
CEP:
```

O sistema retornará uma mensagem confirmando o sucesso da operação.

## Arquivo `usuarios.json`

O arquivo `usuarios.json` é utilizado para armazenar os dados dos usuários cadastrados no sistema. Ele será criado e gerenciado automaticamente pelo sistema.

Exemplo de estrutura do arquivo `usuarios.json`:

```json
[
  {
    "nome": "João Silva",
    "cpf": "12345678900",
    "email": "joao@email.com",
    "cep": "01001000",
    "endereco": "Praça da Sé, São Paulo, SP"
  }
]
```

## Dependências

Este projeto utiliza a biblioteca `Gson` para manipulação de arquivos JSON. Para adicionar o `Gson` ao seu projeto, você pode baixar o JAR da biblioteca ou gerenciar via Maven.

### Download do Gson (JAR)

Baixe a biblioteca `Gson` em [Gson JAR](https://mvnrepository.com/artifact/com.google.code.gson/gson).

### Maven

Adicione a seguinte dependência ao seu arquivo `pom.xml` caso esteja utilizando Maven:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.8</version>
</dependency>
```

## API ViaCEP

A API ViaCEP é utilizada para buscar informações de endereço com base no CEP fornecido. Mais informações podem ser encontradas em: [ViaCEP API](https://viacep.com.br/).


---

## Autor

Desenvolvido por Eduardo Alves.
