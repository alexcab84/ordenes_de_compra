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

## Requisitos

- Tener instalado Java version mayor a 17
- Tener instalado y en ejecucion MongoDB
- Tener instalado y en ejecucion Kafka 4.0
- Tener instalado y en ejecucion Redis

## Instalación

1. Clonar el repositorio:

   	```bash
   	git clone https://github.com/alexcab84/ordenes_de_compra.git
   	cd ordenes_de_compra
   	```
2. Abrir el archivo "application.properties" que se encuentra en la carpeta raiz del proyecto. Se deben modificar los siguientes parametros:
   	- spring.data.mongodb.uri= --> escribir la ruta de conexion a la base de datos MongoDB
   	- spring.kafka.bootstrap-servers= --> escribir la url del servidor kafka, por defecto es localhost:9092
   	
3. Entrar en la carpeta target donde se encuentra el archivo .jar y ejecutarlo con el siguiente comando desde consola: java -jar ordenes_de_compra.jar

