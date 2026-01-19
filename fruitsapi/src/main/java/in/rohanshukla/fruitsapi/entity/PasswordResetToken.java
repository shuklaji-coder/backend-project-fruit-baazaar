package in.rohanshukla.fruitsapi.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "password_reset_tokens")
@Data
public class PasswordResetToken {

    @Id
    private String id;

    private String token;
    private String email;
    private LocalDateTime expiryTime;
}
