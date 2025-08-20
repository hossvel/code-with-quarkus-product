package com.devhoss.service;

import com.devhoss.entity.Product;

import java.util.List;

public interface IProductService {

    public List<Product> listAll();

    public Product findById(Long id);


    public Product create(Product product);


    public Product update(Long id, Product updated);


    public boolean delete(Long id);

}
