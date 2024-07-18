package com.cvorotava.backend.service;

import com.cvorotava.backend.entity.Config;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IConfigService {
    List<Config> findAll();

    void delete();

    Config save(Config entity);
}
