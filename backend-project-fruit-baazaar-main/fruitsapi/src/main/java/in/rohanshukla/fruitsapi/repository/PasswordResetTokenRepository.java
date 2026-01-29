package in.rohanshukla.fruitsapi.repository;

import in.rohanshukla.fruitsapi.entity.PasswordResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository
        extends MongoRepository<PasswordResetToken, String> {

    Optional<PasswordResetToken> findByToken(String token);
}
