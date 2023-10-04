package com.elastic.repository;


import com.elastic.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
public interface ProductRepository extends ElasticsearchRepository<Product,Integer> {
}
