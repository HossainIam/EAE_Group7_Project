package de.hnu.auth;

import de.hnu.data.User;
import de.hnu.data.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository users;

    public AuthController(UserRepository users) {
        this.users = users;
    }

    // --- DTOs (minimal) ---
    public static class AuthRequest {
        public String email;
        public String password;
    }

    public static class AuthResponse {
        public Long userId;
        public String email;

        public AuthResponse(Long userId, String email) {
            this.userId = userId;
            this.email = email;
        }
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest req) {
        if (req.email == null || req.email.isBlank() || req.password == null || req.password.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email and password required");
        }
        if (users.findByEmail(req.email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email already exists");
        }

        User u = new User(); // NOW it is stored in Derby (INSERT)
        u.setEmail(req.email.trim().toLowerCase());
        u.setPassword(req.password); // NOTE: plain text for now (we’ll hash later)
        u = users.save(u);

        return new AuthResponse(u.getId(), u.getEmail());
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        User u = users.findByEmail(req.email == null ? "" : req.email.trim().toLowerCase())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid credentials"));

        if (!u.getPassword().equals(req.password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid credentials");
        }

        return new AuthResponse(u.getId(), u.getEmail());
    }
}
