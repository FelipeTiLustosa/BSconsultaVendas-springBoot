# Consulta de Vendas Spring Boot

## Sobre o Projeto

Sistema de consulta de vendas desenvolvido com Spring Boot que permite realizar consultas de vendas por período e vendedor, além de gerar relatórios de sumário de vendas. O projeto implementa uma API REST com endpoints para consulta de dados de vendas e vendedores.

## Diagrama do Projeto

![Captura de tela 2025-01-21 160035](https://github.com/user-attachments/assets/bc2ba825-e2db-4f05-b73a-30e573322dda)

## Tecnologias Utilizadas

- Java 11
- Spring Boot
- JPA / Hibernate
- Maven
- Banco H2
- Spring Data JPA
- REST API
- Postman

## Funcionalidades

### 1. Relatório de Vendas
- Consulta paginada de vendas com filtros opcionais:
  - Data inicial
  - Data final
  - Nome do vendedor (parcial)
- Retorna dados da venda: id, data, valor e nome do vendedor

### 2. Sumário de Vendas por Vendedor
- Consulta agrupada por vendedor com filtros opcionais:
  - Data inicial
  - Data final
- Retorna total de vendas por vendedor no período

## Endpoints da API

### Consulta de Venda por ID
```
GET /sales/{id}
```

### Relatório de Vendas
```
GET /sales/report
GET /sales/report?minDate=2022-05-01&maxDate=2022-05-31&name=odinson
```

### Sumário de Vendas
```
GET /sales/summary
GET /sales/summary?minDate=2022-01-01&maxDate=2022-06-30
```

## Regras de Negócio

1. **Datas**:
   - Se data final não informada: usa data atual do sistema
   - Se data inicial não informada: usa data de 1 ano antes da data final
   - Implementação usando LocalDate do Java 8+

2. **Filtro por Nome**:
   - Busca case-insensitive por substring do nome
   - Se nome não informado: considera texto vazio

## Estrutura do Projeto

```
src/main/java/com.devsuperior.dsmeta
├── controllers
│   └── SaleController.java
├── dto
│   ├── SaleMinDTO.java
│   └── SellerMinDTO.java
├── entities
│   ├── Sale.java
│   └── Seller.java
├── projections
│   └── SaleAndSellerProjection.java
├── repositories
│   └── SaleRepository.java
└── services
    └── SaleService.java
```

## Como Executar

1. Clone o repositório
```bash
git clone https://github.com/FelipeTiLustosa/BSconsultaVendas-springBoot.git
```

2. Entre na pasta do projeto
```bash
cd BSconsultaVendas-springBoot
```

3. Execute o projeto
```bash
./mvnw spring-boot:run
```

## Banco de Dados

O projeto utiliza banco de dados H2 em memória. Para acessar o console do H2:

- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: [vazio]

## Testes da API

A API pode ser testada usando o Postman ou Insomnia com os seguintes endpoints:

1. Buscar venda por ID:
```
GET http://localhost:8080/sales/1
```

2. Relatório de vendas com filtros:
```
GET http://localhost:8080/sales/report?minDate=2022-05-01&maxDate=2022-05-31&name=odinson
```

3. Sumário de vendas por período:
```
GET http://localhost:8080/sales/summary?minDate=2014-01-01&maxDate=2024-06-30
```

## Collection Postman

Para facilitar os testes, você pode importar a collection do Postman disponível em:
https://www.getpostman.com/collections/dea7904f994cb87c3d12

## Autor

Felipe Ti Lustosa

## Licença

Este projeto está sob a licença [MIT](https://opensource.org/licenses/MIT).
