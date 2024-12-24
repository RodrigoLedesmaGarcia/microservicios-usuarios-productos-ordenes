package com.spring.com.ms_order_service.controller;

import com.spring.com.ms_order_service.models.Order;
import com.spring.com.ms_order_service.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrdenController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/")
    public ResponseEntity<Order> createOrderNew(@RequestBody Order order) {
        if (order == null) {
            return ResponseEntity.badRequest().body(null); // Código 400 si la orden es nula
        }
        try {
            Order createdOrder = orderService.createOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder); // Código 201 si se crea correctamente
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Código 500 si hay un error
        }
    } //---

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrder() {
        List<Order> orders = orderService.getAllOrders();
        if (orders == null || orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Código 204 si no hay contenido
        }
        return ResponseEntity.ok(orders); // Código 200 si se obtienen las órdenes correctamente
    } //---
} // fin de la clase controladora
