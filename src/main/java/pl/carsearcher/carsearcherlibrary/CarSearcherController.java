package pl.carsearcher.carsearcherlibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarSearcherController {

    @Autowired
    CarRepository CarRepository;


    @GetMapping("")
    public List<Car> getAll() {
        return CarRepository.getAll();
    }

    @GetMapping("/color/{color}")
    public List<Car> getByColor(@PathVariable String color) {
        return CarRepository.getByColor(color) ;
     }

    @GetMapping("/brand/{brand}")
    public List<Car> getByBrand(@PathVariable String brand) {

        return CarRepository.getByBrand(brand) ;
    }


    @GetMapping("/date/{date_of_prduction}")
    public List<Car> getByDate(@PathVariable int date_of_prduction)
{       return CarRepository.getByDate(date_of_prduction) ; }

    @GetMapping("/{id}")
    public Car getById(@PathVariable("id") int id) {
        return CarRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Car> car) {

        return CarRepository.save(car);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Car updatedCar) {
        Car car = CarRepository.getById(id);

        if (car != null) {
            car.setBrand(updatedCar.getBrand());
            car.setColor(updatedCar.getColor());
            car.setEngine(updatedCar.getEngine());
            car.setDate_of_production(updatedCar.getDate_of_production());

            CarRepository.update(car);
            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Car updatedCar) {

        Car car = CarRepository.getById(id);

        if (car != null) {
            if (updatedCar.getBrand() != null) car.setBrand(updatedCar.getBrand());
            if (updatedCar.getColor() != null) car.setColor(updatedCar.getColor());

            if (updatedCar.getEngine() != null) car.setEngine(updatedCar.getEngine());

            if (updatedCar.getDate_of_production() != null)
                car.setDate_of_production(updatedCar.getDate_of_production());



            CarRepository.update(car);
            return 1;

        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable ("id") int id  ){

        List <Car> IdFromDB = (List<Car>) CarRepository.getById(id);
        if(IdFromDB.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build().getStatusCodeValue();
        }

        return CarRepository.delete(id) ;
    }

    }



