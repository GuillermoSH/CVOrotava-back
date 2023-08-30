package com.cvorotava.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.service.PlayerService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {
	@Autowired
	private PlayerService playerservice;

	@GetMapping
	public List<Player> getPlayers() {
		return playerservice.findAll();
	}
	
	@GetMapping("/orderedBy/{order}")
	public List<Player> getPlayersOrderedBy(@PathVariable String order) {
		return playerservice.findAllOrdered(order);
	}

	@GetMapping("/category/{category}")
	public List<Player> getPlayersByCategory(@PathVariable String category) {
		return playerservice.findByCategory(category);
	}

	@GetMapping("/{id}")
	public Player getPlayerById(@PathVariable Integer id) {
		return playerservice.findById(id);
	}

	@PostMapping
	public ResponseEntity<?> saveUpdatePlayer(@RequestBody Player player) {
		Player newPlayer;
		HashMap<String, Object> response = new HashMap<>();

		try {
			newPlayer = playerservice.save(player);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "¡El jugador ha sido añadido con éxito!");
		response.put("player", newPlayer);
		return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePlayer(@PathVariable Integer id) {
		Player profesor = playerservice.findById(id);
		HashMap<String, Object> response = new HashMap<>();
		try {
			playerservice.remove(profesor.getId());
		} catch (DataAccessException e) {
			response.put("message", "Error al eliminar el jugador de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "¡El jugador fue eliminado con éxito!");
		return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
	}
}