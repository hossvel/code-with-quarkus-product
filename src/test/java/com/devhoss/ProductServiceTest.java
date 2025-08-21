package com.devhoss;

import com.devhoss.entity.Product;
import com.devhoss.service.IProductService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class ProductServiceTest {

    @Inject
    IProductService service;

    @Test
    public void testCreateAndFind() {
        Product p = new Product("Laptop",1200.0);

        Product saved = service.create(p);
        assertNotNull(saved.getId());

        Product found = service.findById(saved.getId());
        assertEquals("Laptop", found.getName());
    }

    @Test
    public void testListAllAndDelete() {
        Product p = new Product("Mouse",25.0);

        p = service.create(p);

        List<Product> products = service.listAll();
        assertFalse(products.isEmpty());

        assertTrue(service.delete(p.getId()));
    }


    public static String generateProductJson(String name, double price) {
        return "{"
                + "\"name\":\"" + name + "\","
                + "\"price\":" + price +"}";
    }
}
