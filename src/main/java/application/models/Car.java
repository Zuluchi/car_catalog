package application.models;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "license_number", unique = true)
    private String licenseNumber;

    @NotNull
    @Column(nullable = false)
    private String brand;

    private String model;

    private String color;

    @Column(name = "car_year")
    private int year;

    public Car(String licenseNumber, String brand, String model, String color, int year) {
        this.licenseNumber = licenseNumber;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
    }
}
