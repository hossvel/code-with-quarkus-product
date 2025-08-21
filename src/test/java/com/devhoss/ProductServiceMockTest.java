package com.devhoss;

import com.devhoss.entity.Product;
import com.devhoss.service.ProductService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class ProductServiceMockTest {
    @InjectMock
    ProductService mockProductService;



    @Test
    public void testCreateProduct() {
        Product mockProduct = new Product("Laptop",1200.0);

        Mockito.when(mockProductService.create(Mockito.any(Product.class))).thenReturn(mockProduct);

        given()
                .body("{\"name\":\"Laptop\",\"price\":1200}")
                .contentType("application/json")
                .when()
                .post("/products")
                .then()
                .statusCode(201)
                .body("name", equalTo("Laptop"))
                .body("price", equalTo(1200.0f));



    }


}
