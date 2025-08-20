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
        Product p = new Product();
        p.name = "Laptop";
        p.price = 1200.0;

        Product saved = service.create(p);
        assertNotNull(saved.id);

        Product found = service.findById(saved.id);
        assertEquals("Laptop", found.name);
    }

    @Test
    public void testListAllAndDelete() {
        Product p = new Product();
        p.name = "Mouse";
        p.price = 25.0;
        p = service.create(p);

        List<Product> products = service.listAll();
        assertFalse(products.isEmpty());

        assertTrue(service.delete(p.id));
    }
}
