package com.elastic.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.elastic.model.Product;
import com.elastic.service.ElasticSearchService;
import com.elastic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apis/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/findAll")
    public ResponseEntity<Page<Product>>getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Page<Product> allProducts = productService.getAllProducts(page, size);
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        Product insertedProduct = productService.insertProduct(product);
        return new ResponseEntity<>(insertedProduct, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestBody Product updatedProduct) {
        Product updated = productService.updateProduct(updatedProduct, id);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable int id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/matchAll")
    public ResponseEntity<String> matchAll() throws IOException {
        SearchResponse<Map> searchResponse = elasticSearchService.matchAllServices();
        String response = searchResponse.hits().hits().toString();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/matchAllProducts")
    public ResponseEntity<List<Product>> matchAllProducts() throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.matchAllProductsServices();
        List<Hit<Product>> listOfHits = searchResponse.hits().hits();
        List<Product> listOfProducts = new ArrayList<>();
        for (Hit<Product> hit : listOfHits) {
            listOfProducts.add(hit.source());
        }
        return new ResponseEntity<>(listOfProducts, HttpStatus.OK);
    }

    @GetMapping("/matchAllProducts/{fieldValue}")
    public ResponseEntity<List<Product>> matchAllProductsWithName(@PathVariable String fieldValue) throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.matchProductsWithName(fieldValue);
        List<Hit<Product>> listOfHits = searchResponse.hits().hits();
        List<Product> listOfProducts = new ArrayList<>();
        for (Hit<Product> hit : listOfHits) {
            listOfProducts.add(hit.source());
        }
        return new ResponseEntity<>(listOfProducts, HttpStatus.OK);
    }

    @GetMapping("/matchAllProducts/date/{fieldValue}")
    public ResponseEntity<List<Product>> matchProductsWithPurchasedAt(@PathVariable LocalDateTime fieldValue) throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.matchProductsWithPurchasedAt(fieldValue);
        List<Hit<Product>> listOfHits = searchResponse.hits().hits();
        List<Product> listOfProducts = new ArrayList<>();
        for (Hit<Product> hit : listOfHits) {
            listOfProducts.add(hit.source());
        }
        return new ResponseEntity<>(listOfProducts, HttpStatus.OK);
    }

}
