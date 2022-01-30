package application.repository;

import application.dto.CarBrandsCountDto;
import application.dto.CarColorCountDto;
import application.dto.CarYearCountDto;
import application.models.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    @Override
    List<Car> findAll();

    Optional<Car> findByLicenseNumber(String licenseNumber);

    @Query(value = "SELECT NEW application.dto.CarBrandsCountDto(c.brand, count(c.brand)) " +
            "FROM Car c " +
            "GROUP BY c.brand " +
            "ORDER BY count(c.brand) DESC")
    List<CarBrandsCountDto> countCarsGroupByBrand();

    @Query(value = "SELECT NEW application.dto.CarColorCountDto(c.color, count(c.color)) " +
            "FROM Car c " +
            "GROUP BY c.color " +
            "ORDER BY count(c.color) DESC")
    List<CarColorCountDto> countCarsGroupByColor();

    @Query(value = "SELECT '90s' AS Period, count(*) AS Count FROM car WHERE car_year BETWEEN 1990 AND 1999 " +
            "UNION ALL " +
            "SELECT '00s' AS Period, count(*) AS Count FROM car WHERE car_year BETWEEN 2000 AND 2009 " +
            "UNION ALL " +
            "SELECT '10s' AS Period, count(*) AS Count FROM car WHERE car_year BETWEEN 2010 AND 2019 " +
            "UNION ALL " +
            "SELECT '20s' AS Period, count(*) AS Count FROM car WHERE car_year BETWEEN 2020 AND 2029 ",
            nativeQuery = true)
    List<CarYearCountDto> countCarsGroupByAge();
}
