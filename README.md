# 🚀 Selenium Automation Framework - Java 21 (High Performance)

![Selenium CI](https://github.com/KisameFlac/Proyecto_Gradle/actions/workflows/tests.yml/badge.svg)

Este es un framework de automatización profesional diseñado para ejecutar pruebas web de alto rendimiento. Implementa técnicas avanzadas de ingeniería de calidad para garantizar escalabilidad y velocidad.

## 📊 Reporte de Pruebas (Allure)
Cada ejecución genera un reporte interactivo con capturas de pantalla automáticas en caso de fallo o éxito.
👉 **[Ver Reporte Interactivo](https://kisameflac.github.io/Proyecto_Gradle/)**

---

## ⚡ Características Avanzadas
* **Ejecución en Paralelo:** Configurado para ejecutar múltiples tests simultáneamente (Parallel Execution) reduciendo el tiempo de feedback en un 50%.
* **Data-Driven Testing (DDT):** Pruebas basadas en datos utilizando archivos **CSV externos**. La lógica de prueba está separada de los datos.
* **Manejo de Elementos Complejos:** Pruebas robustas para IFrames, Alertas de JavaScript y Tablas dinámicas con selectores XPATH inteligentes.
* **Capturas de Pantalla Automáticas:** Evidencia visual adjunta directamente en el reporte de Allure mediante anotaciones `@Attachment`.

---

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java 21
* **Automatización:** Selenium WebDriver 4
* **Gestor de Dependencias:** Gradle 9.2 (libs.versions.toml)
* **Framework de Pruebas:** JUnit 5 (Parameterized Tests)
* **Reportes:** Allure Report 2.24.0
* **CI/CD:** GitHub Actions (Headless Mode)

---

## 🏗️ Estructura del Proyecto
* `src/test/java`: Contiene los archivos de prueba (`AppTest`, `AdvancedElementsTest`).
* `src/test/resources`: Almacena los archivos de datos (`usuarios.csv`) para las pruebas parametrizadas.
* `gradle/libs.versions.toml`: Gestión centralizada de versiones (Version Catalog).

---

## 🚀 Cómo ejecutar localmente
1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/KisameFlac/Proyecto_Gradle.git](https://github.com/KisameFlac/Proyecto_Gradle.git)