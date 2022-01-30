package application.responses;


import application.dto.CarBrandsCountDto;
import application.dto.CarColorCountDto;
import application.dto.CarYearCountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StatisticsResponse {

    long carsCount;

    List<CarBrandsCountDto> carsByBrand;

    List<CarYearCountDto> carsByAge;

    List<CarColorCountDto> carsByColor;
}
