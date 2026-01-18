package de.hnu.rides;

import de.hnu.data.User;
import de.hnu.data.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/ride-requests")
public class RideRequestController {

    private final RideRequestRepository requests;
    private final UserRepository users;

    public RideRequestController(RideRequestRepository requests, UserRepository users) {
        this.requests = requests;
        this.users = users;
    }

    // ---------- DTO ----------
    public static class CreateRideRequest {
        public Long passengerId;
        public String fromLocation;
        public String toLocation;
        public String earliestDepartureTime;
        public Integer passengerCount;
    }

    // ---------- CREATE REQUEST ----------
    @PostMapping
    public void create(@RequestBody CreateRideRequest req) {

        User passenger = users.findById(req.passengerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid passenger"));

        LocalDateTime time;
        try {
            time = LocalDateTime.parse(req.earliestDepartureTime);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid date");
        }

        if (req.passengerCount == null || req.passengerCount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid passenger count");
        }

        RideRequest r = new RideRequest();
        r.setPassenger(passenger);
        r.setFromLocation(req.fromLocation);
        r.setToLocation(req.toLocation);
        r.setEarliestDepartureTime(time);
        r.setPassengerCount(req.passengerCount);
        r.setStatus(RideRequestStatus.OPEN);

        requests.saveAndFlush(r);
    }

    // ---------- LIST REQUESTS ----------
    public static class RideRequestListItem {
        public Long id;
        public String fromLocation;
        public String toLocation;
        public String earliestDepartureTime;
        public int passengerCount;
        public String status;

        public RideRequestListItem(RideRequest r) {
            this.id = r.getId();
            this.fromLocation = r.getFromLocation();
            this.toLocation = r.getToLocation();
            this.earliestDepartureTime =
                    r.getEarliestDepartureTime() == null ? null : r.getEarliestDepartureTime().toString();
            this.passengerCount = r.getPassengerCount();
            this.status = (r.getStatus() == null) ? "OPEN" : r.getStatus().name();
        }
    }

    @GetMapping
    public List<RideRequestListItem> list() {
        return requests.findAll().stream()
                .map(RideRequestListItem::new)
                .toList();
    }

    @PostMapping("/{id}/accept")
public void accept(@PathVariable Long id, @RequestParam Long driverId) {

    var driver = users.findById(driverId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid driver"));

    if (!driver.isHasCar()) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "only drivers with car can accept");
    }

    var req = requests.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "request not found"));

    if (req.getStatus() != RideRequestStatus.OPEN) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "request is not open");
    }

    req.setStatus(RideRequestStatus.ACCEPTED);
    req.setAcceptedByDriver(driver);

    requests.saveAndFlush(req);
}

}
