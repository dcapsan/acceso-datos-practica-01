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

## Notas sobre el JSON
El campo `articulos` aparece en dos formatos (lista de objetos `{"articulo": {...}}`
u objeto `{"articulo": [...]}`). `LectorPedidos` los admite ambos.

## Unión de datos
`Unidor` indexa los clientes por DNI y asigna cada pedido a su cliente.
Los pedidos cuyo DNI no existe en los clientes se avisan por pantalla.

## Salida
El resultado se guarda en `salida/clientes_pedidos.xml`. Cada pedido lleva su ID como
atributo (`<pedido id="P001">`) y no repite el DNI, que ya está en el cliente.
Se ejecuta con `mvn -q compile exec:java`.

## Estado del proyecto
- [x] Lectura del XML de clientes
- [x] Modelo de datos (Cliente, Pedido, Articulo)
- [x] Lectura del JSON de pedidos
- [x] Unión por DNI
- [x] Eliminar DNI y poner ID como atributo
- [x] Escritura del XML con indentación
- [ ] Fichero de acceso aleatorio
- [ ] Consulta por teclado con validación

## Compilar y ejecutar
    mvn -q compile exec:java