package de.hnu.rides;

import de.hnu.data.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User driver;                               // The user who offers the ride and speciphy as a foreign key relationship to the User entity.

    private String fromLocation;
    private String toLocation;

    private LocalDateTime departureTime;

    private int availableSeats;

    private double totalFuelCost;

    @Enumerated(EnumType.STRING) // An enum is a fixed list of values. An enum is a fixed list of values.
    private RideStatus status;

    // getters & setters
    public Long getId() { return id; }

    public User getDriver() { return driver; }
    public void setDriver(User driver) { this.driver = driver; }

    public String getFromLocation() { return fromLocation; }
    public void setFromLocation(String fromLocation) { this.fromLocation = fromLocation; }

    public String getToLocation() { return toLocation; }
    public void setToLocation(String toLocation) { this.toLocation = toLocation; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public double getTotalFuelCost() { return totalFuelCost; }
    public void setTotalFuelCost(double totalFuelCost) { this.totalFuelCost = totalFuelCost; }

    public RideStatus getStatus() { return status; }
    public void setStatus(RideStatus status) { this.status = status; }
}
