package com.spring.com.ms_product_service.service;

import com.spring.com.ms_product_service.model.Product;
import com.spring.com.ms_product_service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductService{

    @Autowired
    private IProductRepository productRepository;


    @Override
    public List<Product> listarProducto() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Optional<Product> buscarPorId(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product guardar(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void eliminar(Long id) {
         productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductByName(String name) {
        return (List<Product>)productRepository.findByName(name);
    }

    @Override
    public Optional<Product> findProductByPrice(Double price) {
        return (Optional<Product>) productRepository.findByPrice(price);
    }
}
