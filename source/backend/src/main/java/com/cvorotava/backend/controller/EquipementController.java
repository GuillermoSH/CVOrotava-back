package com.cvorotava.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvorotava.backend.entity.Equipement;
import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.service.EquipementService;
import com.cvorotava.backend.service.PlayerService;

@CrossOrigin(origins = {"http://192.168.1.27:4200/", "http://localhost:4200/", "https://zm220cwj-4200.euw.devtunnels.ms/", "https://c24djzb4-4200.uks1.devtunnels.ms/"})
@RestController
@RequestMapping("/api/v1/equipements")
public class EquipementController {
	@Autowired
	private EquipementService equipementservice;
	
	@Autowired
	private PlayerService playerservice;
	
	@GetMapping
	public List<Equipement> getEquipements() {
		return equipementservice.findAll();
	}
	
	@GetMapping("/{id}")
	public Equipement getEquipementById(@PathVariable Integer id) {
		return equipementservice.findById(id);
	}
	
	@GetMapping("/search/{search}")
	public List<Equipement> searchEquipementsBy(@PathVariable String search) {
		if (search.equals("empty")) {
			return equipementservice.findAll();
		}
		return equipementservice.searchLike(search);
	}
	
	@PostMapping
	public ResponseEntity<?> saveEquipement(@RequestBody Equipement equipement) {
		Equipement newEquipement;
		HashMap<String, Object> response = new HashMap<>();

		try {
			newEquipement = equipementservice.save(equipement);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "¡El pago ha sido añadido con éxito!");
		response.put("payment", newEquipement);
		return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> updateEquipement(@RequestBody Equipement equipement) {
		Equipement newPayment;
		HashMap<String, Object> response = new HashMap<>();

		try {
			newPayment = equipementservice.save(equipement);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "¡Se han cambiado los datos con éxito!");
		response.put("payment", newPayment);
		return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{equipement_id}/add/player")
	public ResponseEntity<?> addPlayerToEquipement(@PathVariable Integer equipement_id, @RequestBody Player newPlayer) {
		Player player = null;
		HashMap<String, Object> response = new HashMap<>();
		Equipement payment;
		payment = equipementservice.findById(equipement_id);
		
		List<Player> players = payment.getPlayers();
		player = playerservice.findById(newPlayer.getId());
		
		if (!players.contains(player) || payment == null) {
			players.add(player);
			payment.setPlayers(players);
		} else {
			response.put("message", "Error, el jugador ya tiene esta equipación.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			payment = equipementservice.save(payment);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		response.put("message", "¡Se han cambiado los datos con éxito!");
		return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{equipement_id}/delete/player/{id}")
	public ResponseEntity<?> deletePlayerToEquipement(@PathVariable Integer equipement_id, @PathVariable Integer id) {
		Player player = null;
		HashMap<String, Object> response = new HashMap<>();
		Equipement payment;
		payment = equipementservice.findById(equipement_id);
		
		List<Player> players = payment.getPlayers();
		player = playerservice.findById(id);
		
		try {
			players.remove(player);
			payment.setPlayers(players);
			payment = equipementservice.save(payment);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		response.put("message", "¡Se han cambiado los datos con éxito!");
		return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePlayer(@PathVariable Integer id) {
		Equipement payment = equipementservice.findById(id);
		HashMap<String, Object> response = new HashMap<>();
		try {
			equipementservice.remove(payment.getId());
		} catch (DataAccessException e) {
			response.put("message", "Error al eliminar la equipación de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "¡La equipación fue eliminada con éxito!");
		return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
	}
}
