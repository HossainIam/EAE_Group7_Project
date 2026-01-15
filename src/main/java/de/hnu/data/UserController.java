package de.hnu.users;

import de.hnu.data.User;
import de.hnu.data.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository users;

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

    @GetMapping("/{id}")
    public UserInfoResponse getUser(@PathVariable Long id) {
        User u = users.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        return new UserInfoResponse(u.getId(), u.getEmail(), u.isHasCar());
    }
}
