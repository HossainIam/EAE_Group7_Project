package de.hnu.data;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNIDnumber(String NIDnumber);
    Optional<User> findByDrivingLicenseNumber(String drivingLicenseNumber);
    Optional<User> findByCarNumber(String carNumber);

}
