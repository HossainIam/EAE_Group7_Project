package de.hnu.rides;

import de.hnu.data.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ride_requests")
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User passenger;

    private String fromLocation;
    private String toLocation;

    private LocalDateTime earliestDepartureTime;

    private int passengerCount;
    
    @ManyToOne
    private de.hnu.data.User acceptedByDriver;

    @Enumerated(EnumType.STRING)
    private RideRequestStatus status;

    public Long getId() { return id; }

    public User getPassenger() { return passenger; }
    public void setPassenger(User passenger) { this.passenger = passenger; }

    public String getFromLocation() { return fromLocation; }
    public void setFromLocation(String fromLocation) { this.fromLocation = fromLocation; }

    public String getToLocation() { return toLocation; }
    public void setToLocation(String toLocation) { this.toLocation = toLocation; }

    public LocalDateTime getEarliestDepartureTime() { return earliestDepartureTime; }
    public void setEarliestDepartureTime(LocalDateTime earliestDepartureTime) {
        this.earliestDepartureTime = earliestDepartureTime;
    }

    public int getPassengerCount() { return passengerCount; }
    public void setPassengerCount(int passengerCount) { this.passengerCount = passengerCount; }

    public RideRequestStatus getStatus() { return status; }
    public void setStatus(RideRequestStatus status) { this.status = status; }

    public de.hnu.data.User getAcceptedByDriver() { return acceptedByDriver; }
    public void setAcceptedByDriver(de.hnu.data.User acceptedByDriver) { this.acceptedByDriver = acceptedByDriver; }

}
