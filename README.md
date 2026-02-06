# 🚀 Hybrid Automation Framework - Java 21 (Full Stack QA)

![Selenium CI](https://github.com/KisameFlac/Proyecto_Gradle/actions/workflows/tests.yml/badge.svg)

Este es un framework de automatización de nivel profesional que integra pruebas de **Frontend (UI)** y **Backend (API)** en un solo ecosistema. Diseñado con una arquitectura de alta ingeniería para garantizar escalabilidad, mantenibilidad y velocidad extrema.

## 📊 Reporte de Pruebas (Allure)
Cada ejecución genera un reporte interactivo que incluye:
* 📸 Capturas de pantalla automáticas.
* 🌐 Logs detallados de peticiones y respuestas HTTP (JSON).
* ⏱️ Tiempos de respuesta y trazabilidad total.

👉 **[Ver Reporte Interactivo](https://kisameflac.github.io/Proyecto_Gradle/)**

---

## ⚡ Características de Alto Nivel
* **Arquitectura Híbrida:** Capacidad de realizar pruebas cruzadas. Obtenemos datos vía API (Rest-Assured) para validar la integridad de la interfaz de usuario (Selenium).
* **Patrón de Herencia (Base Classes):** Uso de `BaseTest` y `BaseApiTest` para centralizar la configuración del WebDriver y clientes API, eliminando el código duplicado.
* **Ejecución en Paralelo:** Configurado para ejecutar múltiples hilos simultáneamente, optimizando los tiempos de ejecución en CI/CD.
* **Data-Driven Testing (DDT):** Pruebas parametrizadas mediante archivos **CSV externos**, separando la lógica del negocio de los datos de prueba.
* **Manejo de Elementos Complejos:** Automatización robusta de **IFrames, Alertas JS, Shadow DOM y Tablas Dinámicas** con XPATH avanzado.

---

## 🛠️ Stack Tecnológico
* **Lenguaje:** Java 21 (OpenJDK)
* **UI Testing:** Selenium WebDriver 4
* **API Testing:** Rest-Assured 5.4.0 (con Jackson para manejo de JSON)
* **Gestor de Dependencias:** Gradle 9.2 (utilizando *Version Catalogs* `.toml`)
* **Framework de Pruebas:** JUnit 5
* **Reportes:** Allure Report (Integrado con filtros de red)
* **CI/CD:** GitHub Actions (Optimizado para modo Headless)

---

## 🏗️ Estructura del Proyecto
* `src/test/java/proyectos_gradle/`: 
    * `BaseTest.java`: Configuración madre para UI (Screenshots, Driver management).
    * `BaseApiTest.java`: Configuración madre para API (BaseURI, Allure Filters).
    * `HybridTest.java`: Pruebas de integración Backend-Frontend.
    * `AdvancedElementsTest.java`: Pruebas de componentes web complejos.
* `src/test/resources/`: Archivos de datos (`usuarios.csv`) y configuración.
* `gradle/libs.versions.toml`: Gestión centralizada de versiones.

---

## 🚀 Ejecución Local
1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/KisameFlac/Proyecto_Gradle.git](https://github.com/KisameFlac/Proyecto_Gradle.git)