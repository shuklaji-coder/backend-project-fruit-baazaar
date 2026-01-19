package in.rohanshukla.fruitsapi.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "orders")
public class OrderEntity {

    @Id
    private String id;

    private String userEmail;

    // 🔥 FIXED HERE
    private List<OrderItem> items;

    private double totalAmount;

    private String razorpayOrderId;
    private String paymentId;
    private String paymentStatus;

    private BillingAddress billingAddress;

    private LocalDateTime createdAt;
}
