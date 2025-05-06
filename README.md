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
- Tener instalado Maven (preferiblemente las ultimas versiones, por ejemplo la 3.9.9)
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
   	
3. Abrir la terminal o consola (dependiendo de tu Sistema Operativo) en la ruta raiz del proyecto y ejecutar el siguiente comando para compilar el proyecto:
	```bash
	mvn clean install
	```
	
4. Entrar en la carpeta target donde se encuentra el archivo .jar y ejecutarlo con el siguiente comando desde consola:
	```bash
	java -jar ordenes_de_compra.jar
	```

## Cómo usarlo?

Endpoints disponibles:

	"{{base_url}}" es la url del servidor, por ejemplo http://localhost:8080 o un domain personalizado.

	a) {{base_url}}/ordenes/crear (metodo para crear las ordenes de compra) Metodo POST
	
		Ejemplo del jsonBody que se debe enviar:
		{
			"idUsuario": "prueba_001",
			"items": [{
				"id_producto": "producto_001",
				"cantidad": 3,
				"precio_unitario": 4000.00
			}]
		}
		
		en "items" se puede enviar mas articulos o productos, ya que es una lista de productos.
		
	b)  {{base_url}}/ordenes/lista?page=&size=&id_usuario= (Metodo para listar las ordenes de compra por id de usuario) Metodo GET
	
		Se incluye una paginacion de resultados, se debe colocar el tamano de la pagina en size y el numero de pagina en page. El Id de usuario en el parametro id_usuario