//package ru.itis.springbackend.util.mapper;
//
//import org.mapstruct.AfterMapping;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//import ru.itis.springbackend.dto.request.RegisterRequest;
//import ru.itis.springbackend.model.User;
//
//@Mapper(componentModel = "spring")
//public interface RegisterMapper {
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createDate", ignore = true)
//    @Mapping(target = "updatedDate", ignore = true)
//    @Mapping(target = "balance", constant = "0")
//    @Mapping(target = "role", constant = "USER")
//    @Mapping(target = "status", constant = "ACTIVE")
//    User toEntity(RegisterRequest request);
//
//    @AfterMapping
//    default void setDefaultValues(@MappingTarget User user){
//        user.setToDefault();
//    }
//}
