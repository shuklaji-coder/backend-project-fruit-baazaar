package in.rohanshukla.fruitsapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "fruit")
public class FruitEntity {

    @Id
    private String id;   // ← TRUE ID for MongoDB

    private String name;
    private String category;
    private int pricePerKg;
    private String imageUrl;
    private String description;
}
