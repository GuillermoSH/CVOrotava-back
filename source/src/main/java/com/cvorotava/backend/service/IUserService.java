package com.cvorotava.backend.service;

import com.cvorotava.backend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    List<User> findAll();

    User findById(Integer id);

    User findByUsername(String username);

    User save(User user);

    void delete(Integer id);
}
