package pl.artole.rentcars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CarDto(Long id,
                     @JsonProperty("firma_auta") String mark,
                     @JsonProperty("model_auta") String model,
                     @JsonProperty("rocznik_auta") Long year,
                     @JsonProperty("kolor_auta") String color,
                     @JsonProperty("poj_silnika_auta") String engineCapacity,
                     @JsonProperty("typ_paliwa") String fuelType) {
}
