package com.cvorotava.backend.service;

import com.cvorotava.backend.dto.UserDto;
import com.cvorotava.backend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    List<UserDto> findAll();

    UserDto findById(Integer id);

    UserDto findByUsername(String username);

    UserDto save(User user);

    void delete(UserDto userDto);
}
