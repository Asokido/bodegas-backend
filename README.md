# bodegas-backend

API REST para gestionar bodegas y sus productos. Hecha con Java 17, Spring Boot 3.3, JPA y H2.

Una bodega puede tener varios productos y cada producto pertenece a una sola bodega (relación 1:N).

## Requisitos

- JDK 17 o superior

No es necesario tener Maven instalado, el proyecto trae Maven Wrapper (`mvnw`).

## Ejecutar

Desde la carpeta del proyecto:

```bash
# Windows
mvnw.cmd spring-boot:run

# Linux / Mac
./mvnw spring-boot:run
```

También se puede abrir la carpeta en VS Code (con Extension Pack for Java) y ejecutar `BodegasBackendApplication.java`.

La API queda en `http://localhost:8080`.

## Endpoints

| Método | Ruta       | Acción                                      |
|--------|------------|---------------------------------------------|
| POST   | `/bodegas` | Registrar bodega (`nombre`, `ubicacion`)    |
| GET    | `/bodegas` | Consultar bodegas con sus productos         |

Ejemplo para registrar una bodega:

```bash
curl -X POST http://localhost:8080/bodegas \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Bodega Oriente","ubicacion":"Zona oriente"}'
```

Respuesta (201):

```json
{"id":3,"nombre":"Bodega Oriente","ubicacion":"Zona oriente","productos":[]}
```

Ejemplo de respuesta de `GET /bodegas`:

```json
[
  {
    "id": 1,
    "nombre": "Bodega Norte",
    "ubicacion": "Zona norte",
    "productos": [
      {"id": 1, "nombre": "Computador portátil", "descripcion": "Portátil de uso general", "precio": 2500000.00, "cantidad": 15},
      {"id": 2, "nombre": "Monitor", "descripcion": "Monitor de escritorio", "precio": 750000.00, "cantidad": 20}
    ]
  },
  {
    "id": 2,
    "nombre": "Bodega Sur",
    "ubicacion": "Zona sur",
    "productos": [
      {"id": 3, "nombre": "Impresora", "descripcion": "Impresora láser", "precio": 1200000.00, "cantidad": 8}
    ]
  }
]
```

## Datos de prueba

Al iniciar, `DataInitializer` (CommandLineRunner) guarda con los repositorios las bodegas Norte y Sur y los tres productos del enunciado.

## Base de datos

Se usa H2 en memoria, así que los datos se pierden al detener la aplicación.

- Consola: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:bodegasdb`
- Usuario: `sa`, sin contraseña

## Estructura

```
src/main/java/com/example/bodegas/
├── entity/        Bodega, Producto
├── repository/    BodegaRepository, ProductoRepository
├── service/       BodegaService
├── controller/    BodegaController
├── dto/           BodegaRequest, BodegaResponse, ProductoResponse
└── config/        CorsConfig, DataInitializer
```

## Pruebas

```bash
mvnw.cmd test      # Windows
./mvnw test        # Linux / Mac
```

## Frontend

El frontend está en [bodegas-frontend](https://github.com/Asokido/bodegas-frontend) y corre en `http://localhost:4200`.
