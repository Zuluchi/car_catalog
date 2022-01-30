package application.services;

import application.exceptions.ExistsException;
import application.exceptions.NotFoundException;
import application.exceptions.WrongArgumentException;
import application.models.Car;
import application.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public String addCar(String licenseNumber, String brand, String model, String color, int year)
            throws ExistsException, WrongArgumentException {
        checkRequest(licenseNumber, brand, year);

        if (carRepository.findByLicenseNumber(licenseNumber).isEmpty()) {
            Car car = new Car(licenseNumber, brand, model, color, year);
            carRepository.save(car);
            return "Car added";
        }
        throw new ExistsException("Car with number " + licenseNumber + " exists");
    }

    public String deleteCar(long id) throws NotFoundException {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            carRepository.delete(carOptional.get());
            return "Car deleted";
        }
        throw new NotFoundException("Car with id " + id + " not found");
    }

    public String updateCar(long id, String licenseNumber, String brand, String model,
                            String color, int year) throws WrongArgumentException, NotFoundException {
        checkRequest(licenseNumber, brand, year);

        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();

            if (carRepository.findByLicenseNumber(licenseNumber).isEmpty()
                    || car.getId() == carRepository.findByLicenseNumber(licenseNumber).get().getId()) {
                car.setLicenseNumber(licenseNumber);
            } else throw new WrongArgumentException("Other car with number " + licenseNumber + " exists");
            car.setBrand(brand);
            car.setModel(model);
            car.setColor(color);
            car.setYear(year);
            carRepository.save(car);
            return "Car updated";
        }

        throw new NotFoundException("Car with id " + id + " not found");
    }

    private void checkRequest(String licenseNumber, String brand, int year) throws WrongArgumentException {
        if (!licenseNumber.matches("[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$"))
            throw new WrongArgumentException("Wrong number. " +
                    "Use A111AA111 or A111AA11 format with 'АВЕКМНОРСТУХ' Cyrillic letters");

        if (brand.isEmpty() || brand == null) throw new WrongArgumentException("Brand is empty");

        if (year > Calendar.getInstance().get(Calendar.YEAR) || year < 1990)
            throw new WrongArgumentException("Wrong year. Choose year between 1990 and " +
                    Calendar.getInstance().get(Calendar.YEAR));
    }
}
