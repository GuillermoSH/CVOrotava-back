package com.cvorotava.backend.mapper;

import com.cvorotava.backend.dto.UserDto;
import com.cvorotava.backend.entity.User;
import com.cvorotava.backend.error.exception.NotFoundException;
import com.cvorotava.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserMapperImpl implements IUserMapper {
    private final UserRepository userRepository;

    @Autowired
    public UserMapperImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto userToDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }

    @Override
    public User userDTOToEntity(UserDto userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = userRepository.findByUsername(userDTO.getUsername()).orElseThrow(() -> new NotFoundException("That user couldn't be found"));

        return User.builder()
                .id(user.getId())
                .username(userDTO.getUsername())
                .password(user.getPassword())
                .roles(userDTO.getRoles())
                .build();
    }

    @Override
    public List<UserDto> usersListToDTOList(List<User> userList) {
        if (userList.isEmpty()) {
            return Collections.emptyList();
        }

        List<UserDto> userDTOList = new ArrayList<>();

        for (User user : userList) {
            userDTOList.add(userToDTO(user));
        }

        return userDTOList;
    }

    @Override
    public List<User> dtoListToUserList(List<UserDto> userDTOList) {
        if (userDTOList.isEmpty()) {
            return Collections.emptyList();
        }

        List<User> userList = new ArrayList<>();

        for (UserDto userDTO : userDTOList) {
            userList.add(userDTOToEntity(userDTO));
        }

        return userList;
    }
}
