package pl.artole.rentcars.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mark;
    private String model;
    private Long year;
    private String color;
    private String engineCapacity;
    private String fuelType;

    public Car(String mark, String model, Long year, String color, String engineCapacity, String fuelType) {
        this.mark = mark;
        this.model = model;
        this.year = year;
        this.color = color;
        this.engineCapacity = engineCapacity;
        this.fuelType = fuelType;
    }
}
