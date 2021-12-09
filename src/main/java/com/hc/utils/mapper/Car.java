package com.hc.utils.mapper;

import org.modelmapper.spi.SourceGetter;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ReflectionUtils;

import java.io.FileDescriptor;
import java.lang.reflect.Field;

/**
 * @author hancongcong
 */
public class Car {
    private String make;
    private int numberOfSeats;
    private CarType type;
    private SourceGetter sourceGetter;

    public Car() {
    }

    public Car(String make, int numberOfSeats, CarType type) {
        this.make = make;
        this.numberOfSeats = numberOfSeats;
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public SourceGetter getSourceGetter() {
        return sourceGetter;
    }

    public void setSourceGetter(SourceGetter sourceGetter) {
        this.sourceGetter = sourceGetter;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", type=" + type +
                ", sourceGetter=" + sourceGetter +
                '}';
    }

    public static void main(String[] args){
        Car car = new Car();
        SourceGetter<Car> sourceGetter = Car::getMake;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            car.getMake();
        }
        System.out.println(System.currentTimeMillis() - start);
//        Car car = new Car();
//        Field field = ReflectionUtils.findField(Car.class,"make");
//        ReflectionUtils.makeAccessible(field);
//        ReflectionUtils.setField(field,car,"test");
//        System.out.println(car);
    }
}
