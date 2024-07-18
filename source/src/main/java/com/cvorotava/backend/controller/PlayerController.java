package com.cvorotava.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cvorotava.backend.entity.Payment;
import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.service.PaymentService;
import com.cvorotava.backend.service.PlayerService;

@CrossOrigin(origins = { "http://localhost:4200/" })
@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {
	@Autowired
	private PlayerService playerservice;

	@GetMapping
	public ResponseEntity<List<Player>> getPlayers() {
		return ResponseEntity.ok(playerservice.findAll());
	}

	@GetMapping("/total")
	public ResponseEntity<String[]> getPlayersCount() {
		return ResponseEntity.ok(playerservice.countPlayers());
	}

	@GetMapping("/orderedBy/{order}")
	public ResponseEntity<List<Player>> getPlayersOrderedBy(@PathVariable String order) {
		return ResponseEntity.ok(playerservice.findAllOrderedBy(order));
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<Player>> getPlayersByCategory(@PathVariable String category) {
		return ResponseEntity.ok(playerservice.findByCategory(category));
	}

	@GetMapping("/search/{search}")
	public ResponseEntity<List<Player>> searchPlayersBy(@PathVariable String search) {
		if (search.equals("empty")) {
			return getPlayers();
		}
		return ResponseEntity.ok(playerservice.searchLike(search));
	}

	@GetMapping("/dni/{dni}")
	public ResponseEntity<Player> getPlayerByDni(@PathVariable String dni) {
		return ResponseEntity.ok(playerservice.findByDni(dni));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Player> getPlayerById(@PathVariable Integer id) {
		return ResponseEntity.ok(playerservice.findById(id));
	}

	@PostMapping
	public ResponseEntity<Player> save(@RequestBody Player player) {
		return ResponseEntity.ok(playerservice.save(player));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		playerservice.delete(id);
	}

	@DeleteMapping
	public void deleteAll() {
		playerservice.deleteAll();
	}
}