package com.simplecrud.repositories;

import com.simplecrud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IProductRepository extends JpaRepository<Product, String> {
}
