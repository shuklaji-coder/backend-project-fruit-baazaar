package in.rohanshukla.fruitsapi.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
@Data
public class User {

    @Id
    private String id;

    private String email;
    private String password;
    private String role;
    private String name;

    // 🔐 Forgot Password fields
    private String resetToken;
    private LocalDateTime resetTokenExpiry;
}
