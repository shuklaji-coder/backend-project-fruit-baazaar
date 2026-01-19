package in.rohanshukla.fruitsapi.repository;

import in.rohanshukla.fruitsapi.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    List<OrderEntity> findByUserEmail(String userEmail);

    Optional<OrderEntity> findByRazorpayOrderId(String razorpayOrderId);
}
