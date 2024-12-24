package com.spring.com.ms_order_service.client;

import com.spring.com.ms_order_service.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-product-service", url = "http://localhost:6001/api/products/")
public interface IProductClient {

    @GetMapping("/{id}")
    Product getProductById(@PathVariable Long id);
}
