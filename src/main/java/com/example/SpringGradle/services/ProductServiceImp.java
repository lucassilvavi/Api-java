package com.example.SpringGradle.services;

import com.example.SpringGradle.models.Product;
import com.example.SpringGradle.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    protected ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Product create(Product product) {
        this.productRepository.save(product);
        return product;
    }

    @Override
    public Optional<Product> update(Long id, Product product) {
        return this.productRepository.findById(id).map(record -> {
            record.setNo_name(product.getNo_name());
            record.setNu_quantidade(product.getNu_quantidade());
            Product updated = this.productRepository.save(record);
            return  updated;
        });
    }

    @Override
    public void delete(Long id) {
        this.productRepository.deleteById(id);
    }
}
