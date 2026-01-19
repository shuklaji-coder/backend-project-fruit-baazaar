package in.rohanshukla.fruitsapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Authenticationresponse {
    private String email;
    private String token;
}
