//package com.hc.utils.mapper.modelmapper;
//
//import com.hc.utils.mapper.Car;
//import com.hc.utils.mapper.CarDto;
//import com.hc.utils.mapper.CarType;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//
///**
// * 描述：ModelMapper 配置
// */
//public class ModelMapperConfig {
//    public ModelMapper getModelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//        //默认为standard模式，设置为strict模式
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        // 类型映射代码
//        sourceSonToDestinationSon(modelMapper);
//
//        //验证映射
//        modelMapper.validate();
//
//        // 配置代码
//        return modelMapper;
//    }
//
//    /**
//     * 描述：声明 Source 类转 Destination 类的 Mapper
//     * @param modelMapper
//     * @Date  2019/05/09
//     */
//    private void sourceSonToDestinationSon(ModelMapper modelMapper) {
//        modelMapper.createTypeMap(Car.class, CarDto.class)
//                .addMapping(Car::getMake, CarDto::setMake)
//                .addMapping(Car::getNumberOfSeats, CarDto::setSeatCount)
//                .addMapping(Car::getType, CarDto::setType);
//    }
//
//    public static void main(String[] args){
//        ModelMapperConfig modelMapperConfig = new ModelMapperConfig();
//        Car car = new Car();
//        car.setMake("hc");
//        car.setNumberOfSeats(4);
//        car.setType(CarType.HUGE);
//        ModelMapper modelMapper = modelMapperConfig.getModelMapper();
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 100000; i++) {
//            CarDto carDto = modelMapper.map(car,CarDto.class);
//        }
//        long end = System.currentTimeMillis();
//        System.out.print(end - start);
//    }
//}
