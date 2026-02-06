package proyectos_gradle;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest extends BaseApiTest { // <--- Herencia para la API

    @Test
    @DisplayName("Validar que el servidor esté arriba")
    void testHealthCheck() {
        // Ya no necesitas configurar nada, BaseApiTest ya lo hizo
        get("https://the-internet.herokuapp.com/login")
            .then()
            .statusCode(200)
            .time(lessThan(5000L));
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
            .body("username", notNullValue())
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
            .body("title", equalTo("QA Automation"));
    }
}