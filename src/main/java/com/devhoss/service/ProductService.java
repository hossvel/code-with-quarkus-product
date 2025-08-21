package com.devhoss.service;

import com.devhoss.entity.Product;
import com.devhoss.repository.IProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProductService implements IProductService{

    @Inject
    IProductRepository iProductRepository;

    public List<Product> listAll() {
        return iProductRepository.listAll();
    }

    public Product findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Transactional
    public Product create(Product product) {
        iProductRepository.persist(product);
        return product;
    }

    @Transactional
    public Product update(Long id, Product updated) {
        Product product = iProductRepository.findById(id);
        if (product == null) return null;

        product.setName(updated.getName());
        product.setPrice(updated.getPrice());
        return product;
    }

    @Transactional
    public boolean delete(Long id) {
        return iProductRepository.deleteById(id);
    }


}