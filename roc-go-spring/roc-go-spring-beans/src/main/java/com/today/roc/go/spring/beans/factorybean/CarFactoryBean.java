package com.today.roc.go.spring.beans.factorybean;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月27日 17:51*
 * log.info()
 */
@Component
@Data
public class CarFactoryBean implements FactoryBean<Car> {

    @Autowired
    Car car;

    @Value("本田,SUV,1000000.12")
    private String carInfo = "本田,SUV,1000000.12";

    @Override
    public Car getObject() {
        Car car = new Car();
        String[] split = this.carInfo.split(",");
        car.setName(split[0]);
        car.setType(split[1]);
        car.setPrice(new BigDecimal(split[2]));
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
