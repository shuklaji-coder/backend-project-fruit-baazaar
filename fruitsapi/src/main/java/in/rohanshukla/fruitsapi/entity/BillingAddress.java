package in.rohanshukla.fruitsapi.entity;

import lombok.Data;

@Data
public class BillingAddress {
    private String fullName;
    private String mobile;
    private String street;
    private String city;
    private String state;
    private String pincode;
}
