package pl.carsearcher.carsearcherlibrary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {


    @Autowired
    JdbcTemplate jdbcTemplate ;

    public List<Car> getAll() {
       return   jdbcTemplate.query("SELECT id_car , brand , color , engine , date_of_production FROM car ",
                BeanPropertyRowMapper.newInstance(Car.class));
    }

    public Car getById( int id_car ){
        return jdbcTemplate.queryForObject("SELECT id_car , brand , color , engine , date_of_production FROM car WHERE " +
                        "id_car = ?",
                BeanPropertyRowMapper.newInstance(Car.class),  id_car) ;


    }

    public int save(List<Car> cars) {
        cars.forEach(car -> jdbcTemplate.update("INSERT INTO car ( brand , engine , date_of_production , color ) VALUES( ? , ? , ? ,? ) ",
                 car.getBrand() , car.getEngine() , car.getDate_of_production(), car.getColor()) );

        return 1 ;

    }

    public int update(Car car) {
       return  jdbcTemplate.update("UPDATE car SET  brand=? , color=? , engine=? , date_of_production=? WHERE id_car=?"
                  ,  car.getBrand(), car.getColor(),car.getEngine(),  car.getDate_of_production() , car.getId_car()) ;

    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM car WHERE id_car=?" ,
                id  ) ;
    }

    public List<Car> getByBrand(String brand) {
        return jdbcTemplate.query("SELECT id_car , brand , color , engine , date_of_production FROM car WHERE " +
                "brand = ?" ,BeanPropertyRowMapper.newInstance(Car.class),  brand ) ;
    }

    public List<Car> getByDate(int date_of_prduction) {
        return jdbcTemplate.query("SELECT id_car , brand , color , engine , date_of_production FROM car WHERE " +
                "date_of_production = ?" ,BeanPropertyRowMapper.newInstance(Car.class),  date_of_prduction ) ;
    }

    public List<Car> getByColor(String color){
        return jdbcTemplate.query("SELECT id_car , brand , color , engine , date_of_production FROM car WHERE " +
            "color=?   " ,BeanPropertyRowMapper.newInstance(Car.class),  color ) ;
    }
}
