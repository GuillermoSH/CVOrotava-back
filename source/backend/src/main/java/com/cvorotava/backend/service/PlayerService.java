package com.cvorotava.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.repository.PlayerRepository;

@Service
public class PlayerService {
	@Autowired
	private PlayerRepository playerrepository;

	public Player save(Player entity) {
		if (!entity.getBirthday().isEmpty()) {				
			String[] splittedBirthday = entity.getBirthday().split("[/.-]");
			entity.setBirthday(String.join("-", splittedBirthday[2], splittedBirthday[1], splittedBirthday[0]));
		}
		return playerrepository.save(entity);
	}

	public Player findById(Integer id) {
		return playerrepository.findById(id).get();
	}

	public List<Player> findAll() {
		return playerrepository.findAll();
	}
	
	public List<Player> findAllOrdered(String order) {
		return playerrepository.findAllOrderedByName(Sort.by(order));
	}

	public Boolean remove(Integer id) {
		Optional<Player> currentUser = playerrepository.findById(id);
		if (currentUser.isPresent()) {
			playerrepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public List<Player> findByCategory(String category) {
		return playerrepository.findByCategory(category);
	}
}