package com.example.SpringGradle.services;

import com.example.SpringGradle.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> findAll();

    public Optional<Product> findById(Long id);

    public Product create(Product product);

    public Optional<Product> update(Long id, Product product);

    public void delete(Long id);

}
