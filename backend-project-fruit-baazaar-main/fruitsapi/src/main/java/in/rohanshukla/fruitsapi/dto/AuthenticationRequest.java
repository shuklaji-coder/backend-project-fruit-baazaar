package in.rohanshukla.fruitsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
    private String token;
    private String role;
    private String email;
    private String name;
    private  String password;


    }