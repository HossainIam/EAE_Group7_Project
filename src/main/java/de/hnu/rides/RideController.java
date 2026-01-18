package de.hnu.rides;

import de.hnu.data.User;
import de.hnu.data.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/rides")
public class RideController {

    private final RideRepository rides;
    private final UserRepository users;

    public RideController(RideRepository rides, UserRepository users) {
        this.rides = rides;
        this.users = users;
    }

    // ---------- DTO ----------
    public static class CreateRideRequest {
        public Long driverId;
        public String fromLocation;
        public String toLocation;
        public String departureTime;
        public Integer availableSeats;
        public Double totalFuelCost;
    }

    public static class RideResponse {
        public Long id;
        public String status;

        public RideResponse(Long id, String status) {
            this.id = id;
            this.status = status;
        }
    }

    // ---------- OFFER RIDE ----------
    @PostMapping
    public RideResponse createRide(@RequestBody CreateRideRequest req) {

        if (req.driverId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "driverId required");
        }

        User driver = users.findById(req.driverId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid driver"));

        // ✅ BUSINESS RULE ENFORCEMENT
        if (!driver.isHasCar()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Only users with a car can offer rides");
        }

        if (driver.getDrivingLicenseNumber() == null || driver.getDrivingLicenseNumber().isBlank()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Driving license required");
        }

        if (driver.getCarNumber() == null || driver.getCarNumber().isBlank()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Car number required");
        }

        if (req.availableSeats == null || req.availableSeats <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid seats");
        }

        if (req.totalFuelCost == null || req.totalFuelCost < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid fuel cost");
        }

        LocalDateTime dt;
        try {
            dt = LocalDateTime.parse(req.departureTime);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        }

        Ride r = new Ride();
        r.setDriver(driver);
        r.setFromLocation(req.fromLocation);
        r.setToLocation(req.toLocation);
        r.setDepartureTime(dt);
        r.setAvailableSeats(req.availableSeats);
        r.setTotalFuelCost(req.totalFuelCost);
        r.setStatus(RideStatus.OPEN);

        Ride saved = rides.saveAndFlush(r);

        return new RideResponse(saved.getId(), saved.getStatus().name());
    }
    // ---------- LIST RIDES ----------
   
    public static class RideListItem {
    public Long id;
    public String fromLocation;
    public String toLocation;
    public String departureTime;
    public int availableSeats;
    public double totalFuelCost;
    public String status;

    public RideListItem(Ride r) {
        this.id = r.getId();
        this.fromLocation = r.getFromLocation();
        this.toLocation = r.getToLocation();
        this.departureTime = r.getDepartureTime() == null ? null : r.getDepartureTime().toString();
        this.availableSeats = r.getAvailableSeats();
        this.totalFuelCost = r.getTotalFuelCost();
        this.status = r.getStatus() == null ? null : r.getStatus().name();
    }
    }

    @GetMapping
    public java.util.List<RideListItem> listRides() {
    return rides.findAll().stream()
        .map(RideListItem::new)
        .toList();
    }

}
