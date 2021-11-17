package pl.artole.rentcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.artole.rentcars.entity.Car;


public interface CarRepository extends JpaRepository<Car, Long> {
}
