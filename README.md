# CCM - Customer contract Management

## Descrição
Sistema desktop que está sendo desenvolvido como projeto final no curso de Especialização em Java - [Curso developer TI - Full stack](https://www.devtisul.com.br/).

Atuando em escritório de arquitetura por 5 anos me deparei com a necessidade de ter um local onde serão armazenados os dados de clientes, projetos e contratos, onde pudesse extrair dados importantes para a tomada de decisões na empresa.

Foi criado em linguagem Java, com banco de dados mySQL e frontend com API SWING.

A estrutura backend foi criada com os seguintes pacotes:
- Entity: para a criação das entidades de classe.
- Service: porta de entrada para o backend com métodos disponíveis para a view, uma classe por entidade.
- BO - Business Object: contendo as regras de negócio.
- DAO - Data Access Object: com as classes que realizam a operação com o banco de dados SQL.

## Diagrama de classes UML
O projeto possui quatro entidades principais: Cliente, Projeto, Funcionario e Contrato.

![image](https://user-images.githubusercontent.com/94297628/179095672-6dde3691-65fd-4fc9-a18e-ab6cdf76b72a.png)


## Funções do sistema
- Cadastro de clientes.
- Cadastro de projetos.
- Cadastro de funcionários.
- Emissão de um contrato.

Para emitir o contrato é necessário ter um cliente, projeto e um profissional responsável cadastrados no sistema.

## Futuras funções de emissão de relatórios em breve.
