package in.rohanshukla.fruitsapi.repository;

public class UserResponse {

    private String message;

    // ✅ YE CONSTRUCTOR MISSING THA
    public UserResponse(String message) {
        this.message = message;
    }

    // getter
    public String getMessage() {
        return message;

    }

    // setter
    public void setMessage(String message) {
        this.message = message;
    }
}
