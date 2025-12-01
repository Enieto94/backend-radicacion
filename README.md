# ms-radicacion

Microservicio de Spring Boot reactivo para la gestión de la radicación de documentos, incluyendo la generación de números de secuencia únicos. Este proyecto utiliza un stack moderno y reactivo con Spring WebFlux, R2DBC para la persistencia de datos y Spring Security para la autenticación y autorización mediante OAuth2 Resource Server (JWT).

## Tabla de Contenidos
- [ms-radicacion](#ms-radicacion)
  - [Tabla de Contenidos](#tabla-de-contenidos)
  - [Características](#características)
  - [Tecnologías Utilizadas](#tecnologías-utilizadas)
  - [Configuración del Entorno](#configuración-del-entorno)
    - [Prerrequisitos](#prerrequisitos)
    - [Clonar el Repositorio](#clonar-el-repositorio)
    - [Configuración de la Base de Datos](#configuración-de-la-base-de-datos)
    - [Configuración de Seguridad (OAuth2)](#configuración-de-seguridad-oauth2)
  - [Ejecución del Proyecto](#ejecución-del-proyecto)
    - [Desde la línea de comandos (Maven)](#desde-la-línea-de-comandos-maven)
    - [Desde tu IDE](#desde-tu-ide)
  - [Endpoints de la API](#endpoints-de-la-api)
    - [1. Generar Número de Radicado](#1-generar-número-de-radicado)
    - [2. Obtener Token (Proxy)](#2-obtener-token-proxy)
  - [Seguridad](#seguridad)
  - [Contribución](#contribución)
  - [Licencia](#licencia)

## Características
- **Generación de Secuencias**: Genera números de radicado únicos basados en configuraciones predefinidas (prefijo, año, dígitos).
- **API Reactiva**: Construido con Spring WebFlux para un manejo eficiente y no bloqueante de las solicitudes.
- **Persistencia Reactiva**: Utiliza Spring Data R2DBC para interactuar con bases de datos relacionales de forma reactiva.
- **Seguridad Robusta**: Implementa Spring Security con OAuth2 Resource Server para proteger los endpoints mediante tokens JWT.
- **Configuración de CORS**: Permite la integración con aplicaciones frontend desde diferentes orígenes.

## Tecnologías Utilizadas
- **Java 17**: Lenguaje de programación.
- **Spring Boot 3.2.5**: Framework principal.
  - `spring-boot-starter-webflux`: Para construir APIs reactivas.
  - `spring-boot-starter-data-r2dbc`: Para acceso a datos reactivo.
  - `spring-boot-starter-security`: Para seguridad general.
  - `spring-boot-starter-oauth2-resource-server`: Para protección de recursos con OAuth2 (JWT).
- **PostgreSQL**: Base de datos relacional.
  - `r2dbc-postgresql`: Driver reactivo para PostgreSQL.
- **Lombok**: Para reducir el código boilerplate (getters, setters, constructores).
- **Apache Commons Lang3**: Utilidades de propósito general (ej. `StringUtils`).
- **Maven**: Herramienta de gestión de proyectos y dependencias.

## Configuración del Entorno

### Prerrequisitos
- **Java Development Kit (JDK) 17** o superior.
- **Apache Maven 3.6** o superior.
- **PostgreSQL**: Una instancia de PostgreSQL en ejecución.
- **Proveedor OAuth2**: Un servidor de autorización compatible con OAuth2 (ej. Keycloak) para emitir tokens JWT.

### Clonar el Repositorio
```bash
git clone https://github.com/tu-usuario/ms-radicacion.git
cd ms-radicacion/backend-radicacion
```

### Configuración de la Base de Datos
El proyecto se conecta a una base de datos PostgreSQL. Debes configurar las credenciales en `src/main/resources/application.properties`:

```properties
# Configuración de la base de datos con R2DBC (Reactivo)
spring.r2dbc.url=r2dbc:postgresql://<TU_IP_POSTGRESQL>:5432/<TU_NOMBRE_DB>
spring.r2dbc.username=<TU_USUARIO_DB>
spring.r2dbc.password=<TU_PASSWORD_DB>
```
**Asegúrate de reemplazar los valores entre `< >` con tus propias credenciales.**

### Configuración de Seguridad (OAuth2)
El microservicio actúa como un Resource Server de OAuth2, validando tokens JWT emitidos por un servidor de autorización. Configura los detalles de tu proveedor OAuth2 en `src/main/resources/application.properties`:

```properties
# Config de OAuth2 y JWT para Spring Security
spring.security.oauth2.resourceserver.jwt.issuer-uri=https://<TU_KEYCLOAK_URL>/realms/<TU_REALM>
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=https://<TU_KEYCLOAK_URL>/realms/<TU_REALM>/protocol/openid-connect/certs
```
**Asegúrate de reemplazar los valores entre `< >` con los detalles de tu servidor Keycloak o proveedor OAuth2.**

## Ejecución del Proyecto

### Desde la línea de comandos (Maven)
```bash
mvn clean install
mvn spring-boot:run
```

### Desde tu IDE
Puedes importar el proyecto como un proyecto Maven en tu IDE (IntelliJ IDEA, Eclipse, VS Code) y ejecutar la clase `MsRadicacionApplication`.

## Endpoints de la API

### 1. Generar Número de Radicado
Genera un nuevo número de radicado basado en un tipo específico.

- **URL**: `/api/v1/radicacion/generar-radicado`
- **Método**: `POST`
- **Parámetros de Consulta**:
  - `tipoRadicado` (String, requerido): El código corto del tipo de radicado (ej. "RAD", "TUT").
- **Ejemplo de Solicitud (cURL)**:
  ```bash
  curl -X POST "http://localhost:8081/api/v1/radicacion/generar-radicado?tipoRadicado=RAD" \
       -H "Authorization: Bearer <TU_TOKEN_JWT>"
  ```
- **Ejemplo de Respuesta (JSON)**:
  ```json
  {
    "radicado": "RAD202500001"
  }
  ```
- **Notas**: Este endpoint requiere un token JWT válido en el encabezado `Authorization`.

### 2. Obtener Token (Proxy)
Este endpoint actúa como un proxy para obtener tokens de tu servidor de autenticación.

- **URL**: `/api/v1/tokens/all-platforms`
- **Método**: `POST`
- **Cuerpo de la Solicitud (JSON)**:
  ```json
  {
    "username": "tu_usuario",
    "password": "tu_password"
  }
  ```
- **Ejemplo de Solicitud (cURL)**:
  ```bash
  curl -X POST "http://localhost:8081/api/v1/tokens/all-platforms" \
       -H "Content-Type: application/json" \
       -d '{
             "username": "cprada",
             "password": "SGdea2024*"
           }'
  ```
- **Ejemplo de Respuesta**: La respuesta será el token JWT y otros datos devueltos por tu servidor de autenticación.

## Seguridad
Todos los endpoints, excepto los configurados explícitamente como públicos en `SecurityConfig.java` (como los de Swagger y el de generación de radicado para pruebas), requieren autenticación mediante un token JWT válido. El token debe ser enviado en el encabezado `Authorization` con el prefijo `Bearer`.

## Contribución
Las contribuciones son bienvenidas. Por favor, abre un issue para discutir los cambios propuestos o envía un pull request.

## Licencia
[Especifica tu licencia aquí, ej. MIT, Apache 2.0, etc.]
