
# Projeto de Integração com a FIPE

Este projeto é composto por dois microsserviços (API-1 e API-2) que se integram com a API da FIPE para carregar e gerenciar dados de veículos.

## Visão Geral da Arquitetura

1. **API-1**:
   - Fornece endpoints REST para acionar o carregamento de dados e consultar informações
   - Envia as marcas para uma fila RabbitMQ para processamento assíncrono
   - Utiliza PostgreSQL para armazenamento de dados

2. **API-2**:
   - Consome as marcas da fila RabbitMQ
   - Busca os modelos de veículos na API da FIPE para cada marca
   - Armazena os dados dos veículos no PostgreSQL

## Pré-requisitos

- Docker e Docker Compose
- Java 17
- Maven

## Iniciando o Projeto

1. Inicie a infraestrutura:
   ```bash
   docker-compose up -d
   ```
