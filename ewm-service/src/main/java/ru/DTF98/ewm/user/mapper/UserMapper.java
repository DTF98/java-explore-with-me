package ru.DTF98.ewm.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.DTF98.ewm.user.dto.NewUserRequest;
import ru.DTF98.ewm.user.dto.UserDto;
import ru.DTF98.ewm.user.model.User;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toModel(NewUserRequest newUserRequest);

    UserDto toUserDto(User user);

    List<UserDto> toUserDto(List<User> users);
}
