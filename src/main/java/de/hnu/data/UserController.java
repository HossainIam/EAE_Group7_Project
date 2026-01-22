package de.hnu.users;

import de.hnu.data.User;
import de.hnu.data.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController           // This class handles REST API requests and returns data in JSON format
@RequestMapping("/users") // Base path for controller.

//Repository injection
public class UserController {

    private final UserRepository users; // allow user to interact with user data in the database

    public UserController(UserRepository users) {
        this.users = users;
    }

    public static class UserInfoResponse {
        public Long id;
        public String email;
        public boolean hasCar;

        public UserInfoResponse(Long id, String email, boolean hasCar) {
            this.id = id;
            this.email = email;
            this.hasCar = hasCar;
        }
    }

    @GetMapping("/{id}") // @pathvariable to get user by id, is used to extract a value from the URL and use it inside your method.
    public UserInfoResponse getUser(@PathVariable Long id) {
        User u = users.findById(id) // retrive existing user from database
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        return new UserInfoResponse(u.getId(), u.getEmail(), u.isHasCar());
    }
}
