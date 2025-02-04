package com.biblio.infrastructure.mapper;

import com.biblio.domain.model.UserDto;
import com.biblio.infrastructure.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    List<UserDto> userToUserDtoList(List<User> users);
    List<User> userDtoToUserList(List<UserDto> usersDto);

}
