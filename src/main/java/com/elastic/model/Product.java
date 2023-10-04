package com.elastic.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Product {
    private Integer id;
    private String name;
    private String description;
    private int quantity;
    private double price;
//    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSZZ")
//    @Field(type = FieldType.Date, format = {}, pattern = "uuuu-MM-dd HH:mm:ss")
@Field(type = FieldType.Date, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS", format = {})
    private LocalDateTime purchasedAt;
}