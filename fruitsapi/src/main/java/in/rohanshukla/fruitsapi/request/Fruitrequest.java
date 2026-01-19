package in.rohanshukla.fruitsapi.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fruitrequest {

    private String name;
    private String category;

    private String price;
    private String description;
    private Double pricePerKg;
}
