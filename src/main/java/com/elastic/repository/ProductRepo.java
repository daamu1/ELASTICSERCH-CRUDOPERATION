package com.elastic.repository;


import com.elastic.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
public interface ProductRepo extends ElasticsearchRepository<Product,Integer> {
}
