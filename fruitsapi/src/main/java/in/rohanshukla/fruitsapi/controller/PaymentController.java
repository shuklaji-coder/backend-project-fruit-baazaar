package in.rohanshukla.fruitsapi.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import in.rohanshukla.fruitsapi.entity.OrderEntity;
import in.rohanshukla.fruitsapi.repository.OrderRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    @Autowired
    private RazorpayClient razorpayClient;

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    // CREATE RAZORPAY ORDER
    @PostMapping("/create-order")
    public Map<String, Object> createOrder(@RequestBody Map<String, Object> data) throws Exception {

        int amount = Integer.parseInt(data.get("amount").toString());

        JSONObject options = new JSONObject();
        options.put("amount", amount * 100);
        options.put("currency", "INR");
        options.put("receipt", "rcpt_" + System.currentTimeMillis());

        Order order = razorpayClient.orders.create(options);

        Map<String, Object> res = new HashMap<>();
        res.put("orderId", order.get("id"));
        res.put("amount", order.get("amount"));
        res.put("currency", "INR");
        res.put("key", razorpayKeyId);

        return res;
    }

    // VERIFY PAYMENT
    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody Map<String, String> data) {

        try {
            boolean valid = Utils.verifySignature(
                    data.get("razorpayOrderId") + "|" + data.get("razorpayPaymentId"),
                    data.get("razorpaySignature"),
                    razorpayKeySecret
            );

            if (!valid) {
                return ResponseEntity.badRequest().body("Invalid signature");
            }

            return ResponseEntity.ok("Payment verified");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Verification failed");
        }
    }
}
