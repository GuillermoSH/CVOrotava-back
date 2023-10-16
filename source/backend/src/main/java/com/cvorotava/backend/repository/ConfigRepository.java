package com.cvorotava.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvorotava.backend.entity.Config;

public interface ConfigRepository extends JpaRepository<Config, Integer> {

}
