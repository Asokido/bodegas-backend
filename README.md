# bodegas-backend

Backend en Java 17 + Spring Boot 3.3 para gestionar bodegas y sus productos (relación 1:N).

## Requisitos

- JDK 17 o superior
- Maven (VS Code lo incluye, o instálalo aparte)
- VS Code con la extensión **Extension Pack for Java** (incluye *Spring Boot Dashboard*)

## Cómo ejecutarlo en VS Code

1. Abre la carpeta `bodegas-backend` con **File → Open Folder**.
2. Espera a que VS Code descargue las dependencias de Maven (aparece en la barra inferior).
3. Abre `src/main/java/com/example/bodegas/BodegasBackendApplication.java`.
4. Pulsa **Run** sobre el método `main` (o usa el panel *Spring Boot Dashboard*).

También desde terminal:

```bash
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080`.

## Endpoints

| Método | Ruta       | Acción                                              |
|--------|------------|-----------------------------------------------------|
| POST   | `/bodegas` | Registrar bodega (`nombre`, `ubicacion`)           |
| GET    | `/bodegas` | Consultar bodegas, incluyendo sus productos         |

### Ejemplo: registrar una bodega

```bash
curl -X POST http://localhost:8080/bodegas \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Bodega Oriente","ubicacion":"Zona oriente"}'
```

Respuesta (`201 Created`):

```json
{"id":3,"nombre":"Bodega Oriente","ubicacion":"Zona oriente","productos":[]}
```

### Ejemplo: consultar bodegas

```bash
curl http://localhost:8080/bodegas
```

Respuesta:

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

Al arrancar, `DataInitializer` inserta con los repositorios dos bodegas (Norte y Sur) y tres productos, según la evaluación. Solo inserta si la tabla de bodegas está vacía.

## Base de datos

Se usa **H2 en memoria**: los datos se reinician cada vez que se detiene la aplicación.

- Consola H2: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:bodegasdb`
- Usuario: `sa` · Contraseña: (vacía)

## Estructura

```
src/main/java/com/example/bodegas/
├── BodegasBackendApplication.java
├── entity/        Bodega, Producto (JPA, relación 1:N)
├── repository/    BodegaRepository, ProductoRepository
├── service/       BodegaService
├── controller/    BodegaController (REST)
├── dto/           BodegaRequest, BodegaResponse, ProductoResponse
└── config/        CorsConfig, DataInitializer
```

## Ejecutar pruebas

```bash
mvn test
```
