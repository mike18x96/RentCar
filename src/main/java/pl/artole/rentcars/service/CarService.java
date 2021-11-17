package pl.artole.rentcars.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.artole.rentcars.dto.CarDto;
import pl.artole.rentcars.entity.Car;
import pl.artole.rentcars.mapper.CarMapper;
import pl.artole.rentcars.repository.CarRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CarService {

    final private CarRepository carRepository;
    final private CarMapper carMapper;

    public CarService(final CarRepository carRepository,
                      final CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    //CRUD
    public List<CarDto> getAllCars(){
        var objectFromRepo = carRepository.findAll();
        log.info("cars from repo: {}", objectFromRepo);

        return objectFromRepo
                .stream()
                .map(car -> carMapper.fromEntityToDto(car))
                .collect(Collectors.toList());
    }

    public Optional<CarDto> findCarById(Long id) {
        var result = carRepository.findById(id)
                .map(car -> carMapper.fromEntityToDto(car));

        log.info("trying to find object by id: [{}], result: [{}]", id, result);
        return result;
    }

    public CarDto createNewCar(CarDto newCar) {
        Car entityToSave = carMapper.fromDtoToEntity(newCar);
        Car saved = carRepository.save(entityToSave);
        log.info("creating entity [{}] from provided dto [{}]", saved, newCar);

        return carMapper.fromEntityToDto(saved);
    }

    @Transactional
    public boolean deleteCarById(Long id) {
        log.info("deleting car by id: [{}]", id);

        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CarDto updateCar(CarDto updateCar){
        Car entityToSave = carMapper.fromDtoToEntity(updateCar);
        Car saved = carRepository.save(entityToSave);
        log.info("update entity [{}] from provided dto [{}]", saved, updateCar);

        return carMapper.fromEntityToDto(saved);
    }


}
