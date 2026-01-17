package de.hnu.data; 

//USERS table in database // Entity class for User

import jakarta.persistence.Column; /* Using Jakarta Persistence API all public classes*/
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "users") // Rename the table to "users"
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = true)
  private String lastName;
  
  @Column(unique = true, nullable = false)
  private String email;
  
  @Column(nullable = false)
  private String password;
  // nullable false means it is mandatory field
  @Column(nullable = false)
  private boolean hasCar;

  @Column(unique = true)
  private String drivingLicenseNumber;

  @Column(unique = true)
  private String carNumber;

  @Column(unique = true, nullable = false)
  private String NIDnumber;
  
  // getters & setters
  public Long getId() { return id; }

  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }   
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }

  public String getNIDnumber() { return NIDnumber; }
  public void setNIDnumber(String NIDnumber) { this.NIDnumber = NIDnumber; }

  public boolean isHasCar() { return hasCar; }
  public void setHasCar(boolean hasCar) { this.hasCar = hasCar; }

  public String getDrivingLicenseNumber() { return drivingLicenseNumber; }
  public void setDrivingLicenseNumber(String drivingLicenseNumber) {
    this.drivingLicenseNumber = drivingLicenseNumber;}

  public String getCarNumber() { return carNumber; }
  public void setCarNumber(String carNumber) {
    this.carNumber = carNumber;
}


}