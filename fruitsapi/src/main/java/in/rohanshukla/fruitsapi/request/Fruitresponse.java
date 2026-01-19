package in.rohanshukla.fruitsapi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fruitresponse {
    private String id;
    private String imageUrl;
    private String description;
    private  String name;
    private  String category;
    private int pricePerKg;
}
