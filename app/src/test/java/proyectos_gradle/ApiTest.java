package proyectos_gradle;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @BeforeAll
    static void setup() {
        // Configuración global: Ya no necesitas baseURI dentro de cada test
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    @DisplayName("Validar que el servidor esté arriba")
    void testHealthCheck() {
        // Usamos una URL distinta para este test específico
        get("https://the-internet.herokuapp.com/login")
            .then()
            .statusCode(200)
            .time(lessThan(5000L)); // Aumentamos a 5s por si el server está lento
    }

    @Test
    @DisplayName("Validar datos de un usuario")
    void testGetUserData() {
        given()
            .pathParam("userId", 1)
        .when()
            .get("/users/{userId}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("username", notNullValue()) // Validamos que el campo exista
            .body("id", is(1));
    }

    @Test
    @DisplayName("Crear una nueva publicación (POST)")
    void testCreatePost() {
        Map<String, Object> bodyContent = new HashMap<>();
        bodyContent.put("title", "QA Automation");
        bodyContent.put("body", "Test de API exitoso");
        bodyContent.put("userId", 1);

        given()
            .contentType(ContentType.JSON)
            .body(bodyContent)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("QA Automation"))
            .log().all();
    }
}