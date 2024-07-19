package com.cvorotava.backend.mapper;

import com.cvorotava.backend.dto.UserDto;
import com.cvorotava.backend.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IUserMapper {

    UserDto userToDTO(User user);

    @InheritInverseConfiguration
    User userDTOToEntity(UserDto userDTO);

    List<UserDto> usersListToDTOList(List<User> userList);

    List<User> dtoListToUserList(List<UserDto> userDTOList);
}
