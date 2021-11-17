package pl.artole.rentcars;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.artole.rentcars.entity.Car;
import pl.artole.rentcars.repository.CarRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Log
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private CarRepository carRepository;
    private EntityManager entityManager;


    @Override
    @Transactional
    public void run(String...args) throws Exception {
        log.info("Executing startup actions...");
        carRepository.save(new Car("Alfa Romeo", "156", 2000l, "white", "1900cc", "diesel"));
        carRepository.save(new Car("Fiat", "Punto", 2010l, "black", "1400cc", "gasoline"));
        //Car car = new Car("Alfa Romeo", "156", 2000l, "white", "1900cc", "diesel");
        //log.info("Created player: " + car);
        log.info("List of players from database:");

        List<Car> carsFromDatabase = carRepository.findAll();
        for (Car car : carsFromDatabase) {
            log.info("Retrieved player: " + car);
        }

    }


}