package com.hc.utils.mapper.mapstruct;

import com.hc.utils.mapper.Car;
import com.hc.utils.mapper.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

//    @Mapping(source = "car1.numberOfSeats", target = "seatCount")
//    @Mapping(source = "car1.make", target = "make")
//    @Mapping(source = "car2.type", target = "type")
    void carToCarDto(Car car1, Car car2);
}
