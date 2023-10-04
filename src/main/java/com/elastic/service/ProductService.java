package com.elastic.service;

import com.elastic.exception.ProductNotFoundException;
import com.elastic.model.Product;
import com.elastic.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageable);
    }

    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product, int id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product updatedProduct ;

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

            return productRepository.save(updatedProduct);
        }
        throw new ProductNotFoundException("Product with id " + id + " not found.");
    }


    public void deleteProduct(int id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("Product with id " + id + " not found.");
        }
    }
}
