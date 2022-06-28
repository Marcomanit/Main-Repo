package com.example.rohlik.services;

import com.example.rohlik.models.MissingItemDTO;
import com.example.rohlik.models.Order;
import com.example.rohlik.models.ProductOrderDTO;
import com.example.rohlik.models.ProductQantity;
import com.example.rohlik.repositories.OrderRepository;
import com.example.rohlik.repositories.ProductQuantityRepository;
import com.example.rohlik.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService implements OrderInterface{

    private OrderRepository orderRepository;
    private ProductService productService;
    private ProductQuantityRepository productQuantityRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductService productService, ProductQuantityRepository productQuantityRepository) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.productQuantityRepository = productQuantityRepository;
        Timer timer = new Timer();
        timer.schedule(task, 0, 1000L * 30L);
    }


    @Override
    public ResponseEntity<Object> creatingOrder(List<ProductOrderDTO> productsOrderDTO) {
        List<MissingItemDTO> outOfStock = new ArrayList<>();
        List<ProductQantity> productQantity = createItemsInOrder(productsOrderDTO);

        for (ProductQantity item : productQantity) {
            if (item.getQuantityOfProduct() > item.getProduct().getQuantityInStock()) {
                long missingCount = item.getQuantityOfProduct() - item.getProduct().getQuantityInStock();
                MissingItemDTO missingItemDTO = new MissingItemDTO(item.getProduct(), missingCount);
                outOfStock.add(missingItemDTO);
            }
        }
        if(outOfStock.isEmpty()){
            for (ProductQantity item: productQantity){
                productService.updateProductQuantityInStock(item.getProduct(), item.getQuantityOfProduct());
                productQuantityRepository.save(item);
            }
            Order order = new Order(productQantity);
            orderRepository.save(order);
            for (ProductQantity item: productQantity){
                item.setOrder(order);
                productQuantityRepository.save(item);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(productQantity);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(outOfStock);
        }
    }

    @Override
    public ResponseEntity<Object> cancellationOrder(long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            List<ProductQantity> itemsInOrder = productQuantityRepository.findAllByOrder_Id(id);
            for (ProductQantity item : itemsInOrder) {
                productService.findProductByName(item.getProduct().getName()).setQuantityInStock(productService.findProductByName(item.getProduct().getName()).getQuantityInStock() + item.getQuantityOfProduct());
            }
            orderRepository.delete(order.get());
            return ResponseEntity.status(HttpStatus.OK).body("The order was cancelled.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This order does not exist.");
    }

    @Override
    public ResponseEntity<Object> paymantOfTheOrder(long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            if (order.get().isPaymant()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment was already made.");
            } else {
                order.get().setPaymant(true);
                orderRepository.save(order.get());
                return ResponseEntity.status(HttpStatus.OK).body("Payment done");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This order does not exist.");
    }

    @Override
    public List<ProductQantity> createItemsInOrder(List<ProductOrderDTO> productsOrderDTO) {
        List<ProductQantity> itemsInOrder = new ArrayList<>();
        for (ProductOrderDTO product : productsOrderDTO) {
            itemsInOrder.add(new ProductQantity(productService.findProductByName(product.getName()), product.getCount()));
        }
        return itemsInOrder;
    }


    TimerTask task = new TimerTask() {
        public void run() {
            Date currentDate = new Date(System.currentTimeMillis() - 1000 * 60);
            List<Order> orderList = orderRepository.findAllByPaymantAndCreationDateBefore(false, currentDate);

            for (Order order : orderList) {
                cancellationOrder(order.getId());
                System.out.println("Order with id " + order.getId() + " was cancelled");
            }
            System.out.println(currentDate + " This is current date");
        }
    };
}


