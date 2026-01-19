package in.rohanshukla.fruitsapi.service;

import in.rohanshukla.fruitsapi.entity.User;
import in.rohanshukla.fruitsapi.repository.UserRepository;
import in.rohanshukla.fruitsapi.repository.UserRequest;
import in.rohanshukla.fruitsapi.repository.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse registerUser(UserRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return new UserResponse("User Registered Successfully");
    }
}
