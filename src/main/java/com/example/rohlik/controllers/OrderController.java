package com.example.rohlik.controllers;

import com.example.rohlik.models.ProductOrderDTO;
import com.example.rohlik.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody List<ProductOrderDTO> productsOrderDTO){
        return orderService.creatingOrder(productsOrderDTO);
    }

    @GetMapping("/cancel/{id}")
    public ResponseEntity<Object> cancelOrder(@PathVariable long id){
        return orderService.cancellationOrder(id);
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<Object> paymentOrder(@PathVariable long id){
        return orderService.paymantOfTheOrder(id);
    }

}
