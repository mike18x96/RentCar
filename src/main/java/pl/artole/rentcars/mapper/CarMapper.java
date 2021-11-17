package pl.artole.rentcars.mapper;

import org.springframework.stereotype.Component;
import pl.artole.rentcars.dto.CarDto;
import pl.artole.rentcars.entity.Car;

@Component
public class CarMapper implements Mapper<Car, CarDto> {

    @Override
    public CarDto fromEntityToDto(Car car){
        return new CarDto(car.getId(),
                    car.getMark(),
                    car.getModel(),
                    car.getYear(),
                    car.getColor(),
                    car.getEngineCapacity(),
                    car.getFuelType());
    }

    @Override
    public Car fromDtoToEntity(CarDto carDto) {
        return Car.builder()
                .id(carDto.id())
                .mark(carDto.mark())
                .model(carDto.model())
                .year(carDto.year())
                .color(carDto.color())
                .engineCapacity(carDto.engineCapacity())
                .fuelType(carDto.fuelType())
                .build();
    }
}
