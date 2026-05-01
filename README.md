# Laboratorio: Pruebas Unitarias y de Integración (Post-Contenido 2, Unidad 9)

Este proyecto es un microservicio de gestión de productos desarrollado con Spring Boot 3.3.x, enfocado en demostrar un enfoque riguroso de pruebas de integración e implementación de flujos de CI/CD utilizando GitHub Actions.

## Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.3.x** (Web, Data JPA)
- **Base de Datos H2** (Base de datos en memoria para el entorno de pruebas)
- **JUnit 5 & Mockito** (Frameworks de pruebas y simulación)
- **JaCoCo** (Medición de cobertura de código)
- **GitHub Actions** (Integración Continua)

## Arquitectura y Estrategia de Pruebas

El sistema está diseñado con una arquitectura en capas (Domain, Repository, Service, Controller). Para garantizar el correcto funcionamiento de todos los componentes, se han implementado las siguientes estrategias de prueba:

1. **Pruebas de Persistencia (`@DataJpaTest`):** 
   Se valida la interacción directa con la base de datos H2 en un entorno aislado. Se comprueba que las entidades se persistan correctamente, la asignación de IDs automáticos y las consultas personalizadas del repositorio, asegurando la integridad a nivel SQL sin cargar todo el contexto web.

2. **Pruebas de Capa Web (`@WebMvcTest`):**
   Se evalúan los endpoints REST del `ProductoController`. Estas pruebas aíslan la capa web mediante el mockeo del `ProductoService` con `@MockBean`. Esto permite validar de forma rápida la serialización/deserialización JSON, los códigos de estado HTTP (200, 201, 404) y el enrutamiento correcto de la API.

## Integración Continua (CI/CD)

El proyecto cuenta con un pipeline de GitHub Actions (`.github/workflows/ci.yml`) que se ejecuta automáticamente ante cada `push` o `pull_request` a la rama `main`.
El pipeline realiza las siguientes tareas:
- Levanta un entorno Ubuntu limpio.
- Configura Java 21 y Maven.
- Ejecuta todas las pruebas unitarias y de integración (`mvn verify`).
- Genera el reporte de cobertura de JaCoCo.
- Sube el reporte de JaCoCo como artefacto descargable en GitHub.

## Ejecución Local

Para ejecutar las pruebas y generar el reporte de cobertura localmente:

```bash
mvn clean verify
```

Una vez finalizado, puedes visualizar el reporte de cobertura de JaCoCo abriendo el siguiente archivo en tu navegador:
`target/site/jacoco/index.html`

## Documentación y Evidencia

En la carpeta `/docs` se encuentran las capturas de pantalla que evidencian la ejecución exitosa del pipeline de GitHub Actions y los niveles de cobertura obtenidos por JaCoCo, validando el correcto estado del proyecto.
