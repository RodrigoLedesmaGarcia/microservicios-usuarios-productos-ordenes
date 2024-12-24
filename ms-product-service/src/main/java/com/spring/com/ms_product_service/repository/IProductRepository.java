package com.spring.com.ms_product_service.repository;

import com.spring.com.ms_product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    // buscar el producto por nombre
    List<Product> findByName(String name);

    // buscar producto por precio
    Optional<Product> findByPrice(Double price);
}
