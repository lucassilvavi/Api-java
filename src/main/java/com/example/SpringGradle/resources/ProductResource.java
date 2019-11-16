package com.example.SpringGradle.resources;

import com.example.SpringGradle.models.Product;
import com.example.SpringGradle.repository.ProductRepository;
import com.example.SpringGradle.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@Api("API REST - Model Product")
public class ProductResource {
    @Autowired
    ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation("Find all product in database")
    @GetMapping(produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAll() {
        return this.productService.findAll();
    }

    @ApiOperation(value = "Find by ID in database")
    @GetMapping("{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Optional<Product> find(@PathVariable(value = "id") Long id) {
        return productService.findById(id);
    }

    @ApiOperation("Create a new Product")
    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Product product, Errors errors) {
        if (!errors.hasErrors()) {
            Product productCreated = this.productService.create(product);
            return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
        }
        return ResponseEntity
                .badRequest()
                .body(errors
                        .getAllErrors()
                        .stream()
                        .map(msg -> msg.getDefaultMessage())
                        .collect(Collectors.joining(",")));

    }

    @ApiOperation("Update a Product by ID")
    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Product product, Errors errors) {

        if (!errors.hasErrors()) {
            Optional productUpdated = this.productService.update(id, product);

            return new ResponseEntity<>(productUpdated, HttpStatus.CREATED);
        }
        return ResponseEntity
                .badRequest()
                .body(errors
                        .getAllErrors()
                        .stream()
                        .map(msg -> msg.getDefaultMessage())
                        .collect(Collectors.joining(",")));
    }

    @ApiOperation("Delete Product by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.productService.delete(id);
    }
}
