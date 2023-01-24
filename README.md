# Hotelaria API
Api para gerenciamento de hotel​

## Teste bancada Sicredi
Desenvolver uma aplicação que trate do gerenciamento de clientes, funcionários, e quartos do hotel.

## Regras de negócio
 - Cadastro
   - Cliente - Funcionários
     - Não pode ter menos que 18 anos.
     - Ter CPF válido.
   - Quarto 
     - Não pode ter nomes iguais.
     - Devem possuir um valor da diária.
   - Cartão de crédito
     - Deve ser válido (é verificado)
 - Delete
   - Os deletes são físicos com ID
 - Situacionais
   - Data de Check-in tem que ser anterior a data do Check-out.
 - Visualizações
   - Disponível por número de quarto ou CPF.

## Pré-requisitos

- Java 15
- Spring 2.3.3.RELEASE
- JDK 19.0.1
- Maven
- PostgreSQL 15


## Configurações de ambiente

   - Utilize uma IDE de sua preferencia
   - Clone o projeto na sua máquina
   - Localize a o arquivo pom.xml e instale as dependencias
   - Instale o banco de dados postgreSQL e configure o application.yml
   - Logo após rode os scripts de criação do banco e das tabelas indicado no caminho abaixo

## Caminho do banco de dados
- DDL create database: /src/main/resources/db/1-script-create-database.sql
- DDL create tables: /src/main/resources/db/2-script-create-table.sql

## Rodar o projeto
- Vá para  /hotel/src/main/java/com.company.hotelaria.hotel e localize HotelApplication
- Abra o arquivo e na parte superior a direita rode a aplicação

## Visualizar End-points

   - Acesse http://localhost:8080/api/v1/swagger-ui/index.html?configUrl=/api/v1/docs/swagger-config#
    Para visualizar o swagger



