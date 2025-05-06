# Órdenes de Compra

Este proyecto es una API REST desarrollada con Spring Boot que permite la **creación y consulta de órdenes de compra**, utilizando una arquitectura moderna con soporte para mensajería asíncrona y caché en memoria.

## Tecnologías utilizadas

- **Java 17+**
- **Spring Boot**
- **MongoDB** – Base de datos NoSQL para persistencia de órdenes
- **Apache Kafka** – Para comunicación asíncrona
- **Redis** – Para caching y mejora de rendimiento
- **Maven** – Gestión de dependencias y construcción

## Características

- Crear órdenes de compra con datos básicos (productos, usuario, fecha, etc.)
- Listar órdenes existentes
- Publicación de eventos a través de Kafka
- Caché de resultados mediante Redis

## Instalación

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/tuusuario/nombre-del-repositorio.git
   cd nombre-del-repositorio
