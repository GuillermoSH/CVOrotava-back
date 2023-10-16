package com.cvorotava.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvorotava.backend.entity.Config;
import com.cvorotava.backend.repository.ConfigRepository;

@Service
public class ConfigService {
	@Autowired
	private ConfigRepository configurationrepository;
	
	public Config save(Config entity) {
		return configurationrepository.save(entity);
	}

	public List<Config> findAll() {
		return configurationrepository.findAll();
	}

	public Boolean remove(Integer id) {
		Optional<Config> currentConfiguration = configurationrepository.findById(id);
		if (currentConfiguration.isPresent()) {
			configurationrepository.deleteById(id);
			return true;
		}
		return false;
	}
}
