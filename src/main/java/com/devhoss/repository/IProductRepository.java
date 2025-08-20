package com.devhoss.repository;

import com.devhoss.entity.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IProductRepository implements PanacheRepository<Product> {
    // Custom queries if needed
}
