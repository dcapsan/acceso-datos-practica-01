# Práctica 01 - Acceso a datos

Fusión de una base de datos de clientes (XML) y otra de pedidos (JSON)
en un único XML, con escritura y consulta en fichero de acceso aleatorio.

## Tecnologías
- Java 17
- Maven
- DOM (javax.xml) para XML
- Gson para JSON

## Estructura
- `datos/`: ficheros de entrada (`clientes.xml`, `pedidos.json`).
- `src/main/java/practica/`: código fuente.

## Estado del proyecto
- [x] Lectura del XML de clientes
- [x] Modelo de datos (Cliente, Pedido, Articulo)
- [ ] Lectura del JSON de pedidos
- [ ] Unión por DNI
- [ ] Eliminar DNI y poner ID como atributo
- [ ] Escritura del XML con indentación
- [ ] Fichero de acceso aleatorio
- [ ] Consulta por teclado con validación

## Compilar y ejecutar
    mvn compile
    java -cp target/classes practica.Main