package com.devhoss;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;

@QuarkusTest
public class ProductResourceTest {

    @Test
    public void testCreateProduct() {
        given()
                .contentType("application/json")
                .body("{\"name\":\"Laptop\",\"price\":1200}")
                .when()
                .post("/products")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Laptop"))
                .body("price", equalTo(1200f));
    }

    @Test
    public void testGetAllProducts() {
        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }
}
