package application.services;

import application.repository.CarRepository;
import application.responses.StatisticsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    @Autowired
    private CarRepository carRepository;

    public StatisticsResponse getStatistics() {
        return new StatisticsResponse(carRepository.count(), carRepository.countCarsGroupByBrand(),
                carRepository.countCarsGroupByAge(), carRepository.countCarsGroupByColor());
    }
}
