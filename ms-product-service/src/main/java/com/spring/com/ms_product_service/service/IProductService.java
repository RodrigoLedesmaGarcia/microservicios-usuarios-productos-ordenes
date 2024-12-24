package com.spring.com.ms_product_service.service;

import com.spring.com.ms_product_service.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> listarProducto();
    Optional<Product> buscarPorId(Long id);
    Product guardar(Product product);
    void eliminar(Long id);


    List<Product> findProductByName(String name);

    Optional<Product> findProductByPrice(Double price);
}
