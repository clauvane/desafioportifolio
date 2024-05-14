# Sistema de Portifólio

## Descrição
Sistema responsável por gerenciar pessoas, projetos e membros de projetos

## Tecnologias
- Java 11
- Spring Boot
- Spring Data
- Spring Web
- JPA
- Postgres
- Lombok
- JSP
- Bootstrap
- JUnit5

## Pré-requisitos

Possuir um banco postgres com o banco de dados `portifolio` criado
ou executar o seguinte comando:

``` 
docker-compose up -d 
```

## Como rodar o projeto

```
mvn spring-boot:run
```

## Como rodar os testes

```
mvn test
```

### API para Membros

## Listar todos os Membros

```
curl --location 'http://localhost:8080/api/membros'
```

## Buscar Membro pelo id

```
curl --location 'http://localhost:8080/api/membros/{idPessoa}/{idProjeto}'
```

## Criar Membro

```
curl --location 'http://localhost:8080/api/membros' \
--header 'Content-Type: application/json' \
--data '{
    "idPessoa": {idPessoa},
    "idProjeto": {idProjeto}
}'
```

## Atualizar Membro

```
curl --location --request PUT 'http://localhost:8080/api/membros/1/5' \
--header 'Content-Type: application/json' \
--data '{
    "idPessoa": {idPessoa},
    "idProjeto": {idProjeto}
}'
```

## Deletar Membro

```
curl --location --request DELETE 'http://localhost:8080/api/membros/1/5' \
--header 'Content-Type: application/json' \
--data '{
    "idPessoa": {idPessoa},
    "idProjeto": {idProjeto}
}'
```