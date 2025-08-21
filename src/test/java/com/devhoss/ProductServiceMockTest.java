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

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

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

    @Test
    void testGetAllProducts() {
        List<Product> mockProducts = List.of(
                new Product(1L, "Laptop", 1299.99),
                new Product(2L, "Phone", 799.99)
        );

        Mockito.when(mockProductService.listAll()).thenReturn(mockProducts);

        given()
                .when().get("/products")
                .then()
                .statusCode(200)
                .body("$.size()", is(2))
                .body("[0].name", equalTo("Laptop"))
                .body("[1].price", equalTo(799.99f));



    }



}
