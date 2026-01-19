package in.rohanshukla.fruitsapi.entity;

import lombok.Data;

@Data
public class CartItem {
    private String productId;
    private String name;
    private int quantity;
    private double price;
}
