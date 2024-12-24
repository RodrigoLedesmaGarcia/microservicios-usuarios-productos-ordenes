package com.spring.com.ms_order_service.service;

import com.spring.com.ms_order_service.client.IProductClient;
import com.spring.com.ms_order_service.client.IUserClient;
import com.spring.com.ms_order_service.models.Order;
import com.spring.com.ms_order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IUserClient userClient;

    @Autowired
    private IProductClient productClient;

    public Order createOrder(Order order){
        if (userClient.getUserById(order.getUserId()) == null){
            throw new RuntimeException("Usuario no encontrado");
        }
        if (productClient.getProductById(order.getProductoId()) == null){
            throw new RuntimeException("producto no encontrado");
        }

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
