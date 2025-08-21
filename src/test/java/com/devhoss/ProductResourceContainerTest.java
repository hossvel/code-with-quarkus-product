package com.devhoss;

import com.devhoss.config.PostgresTestResource;
import com.devhoss.entity.Product;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
public class ProductResourceContainerTest {

    @Test
    void testAddProduct() {
        given()
                .contentType("application/json")
                .body(new Product("Tablet", 499.99))
                .when()
                .post("/products")
                .then()
                .statusCode(201);
    }

    @Test
    void testGetProducts() {
        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }
}