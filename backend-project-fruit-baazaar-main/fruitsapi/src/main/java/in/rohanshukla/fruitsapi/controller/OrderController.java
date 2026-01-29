package in.rohanshukla.fruitsapi.controller;

import in.rohanshukla.fruitsapi.entity.OrderEntity;
import in.rohanshukla.fruitsapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    // ✅ PLACE ORDER
    @PostMapping("/place")
    public OrderEntity placeOrder(@RequestBody OrderEntity order) {

        // 🔍 DEBUG
        System.out.println("ITEMS RECEIVED => " + order.getItems());
        System.out.println("BILLING => " + order.getBillingAddress());

        // ✅ VALIDATION
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new RuntimeException("Order items missing");
        }

        if (order.getUserEmail() == null || order.getUserEmail().isEmpty()) {
            throw new RuntimeException("userEmail missing");
        }

        // ✅ DEFAULT VALUES
        order.setCreatedAt(LocalDateTime.now());

        if (order.getPaymentStatus() == null || order.getPaymentStatus().isEmpty()) {
            order.setPaymentStatus("COD");
        }

        return orderRepository.save(order);
    }

    // ✅ USER - MY ORDERS
    @GetMapping("/my-orders/{email}")
    public List<OrderEntity> myOrders(@PathVariable String email) {
        return orderRepository.findByUserEmail(email);
    }

    // ✅ ADMIN - ALL ORDERS
    @GetMapping("/all")
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }
}
