package in.rohanshukla.fruitsapi.service;

import in.rohanshukla.fruitsapi.repository.UserRequest;
import in.rohanshukla.fruitsapi.repository.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRequest request);

}
