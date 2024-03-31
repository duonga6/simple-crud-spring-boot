package com.simplecrud.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID_STRING")
    @GenericGenerator(name = "UUID_STRING", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(nullable = false, columnDefinition = "NVARCHAR(50)")
    private String name;

    private String image;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private int quantity;

    public Product(String name, String image, Double price, int quantity) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }
}
