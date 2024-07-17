package com.cvorotava.backend.repository;

import com.cvorotava.backend.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, Integer> {

}
