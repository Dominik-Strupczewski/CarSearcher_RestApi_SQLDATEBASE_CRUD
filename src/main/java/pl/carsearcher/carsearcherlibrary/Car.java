package pl.carsearcher.carsearcherlibrary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Car {


    private int id_car ;
    private String brand ;
    private String color ;
    private String engine ;
    private String date_of_production ;
}
