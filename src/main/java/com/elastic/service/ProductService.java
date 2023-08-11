package com.elastic.service;

import com.elastic.model.Product;
import com.elastic.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public Iterable<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product insertProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct(Product product, int id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        Product updatedProduct = null;

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            // Use builder pattern to create the updated product
            updatedProduct = Product.builder()
                    .id(existingProduct.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .quantity(product.getQuantity())
                    .price(product.getPrice())
                    .build();

            return productRepo.save(updatedProduct);
        }
        throw new RuntimeException("Product with id " + id + " not found.");
    }


    public void deleteProduct(int id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isPresent()) {
            productRepo.deleteById(id);
        } else {
            throw new RuntimeException("Product with id " + id + " not found.");
        }
    }
}
