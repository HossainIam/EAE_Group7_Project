package de.hnu.auth;

import de.hnu.data.User;
import de.hnu.data.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


// @RequestBody this is telling spring to map json that data sended from client to this object 


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository users;

    public AuthController(UserRepository users) {
        this.users = users;
    }

    // --- DTOs means Data Transfer Objects ---
    // AuthRequest carries data FROM client TO server.
    public static class AuthRequest {
        public String FirstName;
        public String LastName;
        public String email;
        public String password;
        public String NIDnumber;
        public Boolean hasCar;
        public String drivingLicenseNumber;
        public String carNumber;

    }
    // AuthResponse carries data FROM server TO client.
    public static class AuthResponse {
        public Long userId;
        public String email;

        public AuthResponse(Long userId, String email) {
            this.userId = userId;
            this.email = email;
        }
    }
    // requestbody to map json data
    // postmapping normal work is sending data to server 
   @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest req) {

    String email = req.email == null ? "" : req.email.trim().toLowerCase();
    String password = req.password == null ? "" : req.password.trim();
    String firstName = req.FirstName == null ? "" : req.FirstName.trim();
    String lastName = req.LastName == null ? "" : req.LastName.trim();
    String nid = req.NIDnumber == null ? "" : req.NIDnumber.trim();
    boolean hasCar = req.hasCar != null && req.hasCar;
    String dl = req.drivingLicenseNumber == null ? "" : req.drivingLicenseNumber.trim();
    String carNo = req.carNumber == null ? "" : req.carNumber.trim();

    if (email.isBlank() || password.isBlank() || firstName.isBlank() || nid.isBlank()) {
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "FirstName, NIDnumber, email, password are required"
        );
    }

    // Check email uniqueness
    if (users.findByEmail(email).isPresent()) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "email already exists");
    }

    // Check NID uniqueness (BEFORE saving)
    if (users.findByNIDnumber(nid).isPresent()) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "NIDnumber already exists");
    }
    if (hasCar) {
    if (dl.isBlank()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Driving license number is required if user has a car");
    }
    if (carNo.isBlank()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Car number is required if user has a car");
    }

    if (users.findByDrivingLicenseNumber(dl).isPresent()) {
        throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Driving license number already exists");
    }

    if (users.findByCarNumber(carNo).isPresent()) {
        throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Car number already exists");
    }
}
    // creating user and setting fields 
    User u = new User();
    u.setEmail(email);
    u.setPassword(password); // later hash
    u.setFirstName(firstName);
    u.setLastName(lastName.isBlank() ? null : lastName);
    u.setNIDnumber(nid);
    u.setHasCar(hasCar);
    u.setDrivingLicenseNumber(hasCar ? dl : null);
    u.setCarNumber(hasCar ? carNo : null);

    // Saving user and handling potential conflicts
    try {
        u = users.saveAndFlush(u); 
    } catch (Exception ex) {
        throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "email or NIDnumber already exists"
        );
    }

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
