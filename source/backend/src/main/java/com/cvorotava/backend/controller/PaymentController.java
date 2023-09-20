package com.cvorotava.backend.controller;

import java.util.ArrayList;
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

import com.cvorotava.backend.entity.Payment;
import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.service.PaymentService;
import com.cvorotava.backend.service.PlayerService;

@CrossOrigin(origins = {"http://192.168.1.27:4200/", "http://localhost:4200/", "https://zm220cwj-4200.euw.devtunnels.ms/", "https://c24djzb4-4200.uks1.devtunnels.ms/"})
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
	@Autowired
	private PaymentService paymentservice;
	
	@Autowired
	private PlayerService playerservice;
	
	@GetMapping
	public List<Payment> getPayments() {
		return paymentservice.findAll();
	}
	
	@GetMapping("/{id}")
	public Payment getPaymentById(@PathVariable Integer id) {
		return paymentservice.findById(id);
	}
	
	@GetMapping("/search/{search}")
	public List<Payment> searchPaymentsBy(@PathVariable String search) {
		if (search.equals("empty")) {
			return paymentservice.findAll();
		}
		return paymentservice.searchLike(search);
	}
	
	@PostMapping
	public ResponseEntity<?> savePayment(@RequestBody Payment payment) {
		Payment newPayment;
		HashMap<String, Object> response = new HashMap<>();

		try {
			newPayment = paymentservice.save(payment);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "¡El pago ha sido añadido con éxito!");
		response.put("payment", newPayment);
		return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> updatePayment(@RequestBody Payment payment) {
		Payment newPayment;
		HashMap<String, Object> response = new HashMap<>();

		try {
			newPayment = paymentservice.save(payment);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "¡Se han cambiado los datos con éxito!");
		response.put("payment", newPayment);
		return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{payment_id}/add/player")
	public ResponseEntity<?> addPlayerToPayment(@PathVariable Integer payment_id, @RequestBody Player newPlayer) {
		Player player = null;
		HashMap<String, Object> response = new HashMap<>();
		Payment payment;
		payment = paymentservice.findById(payment_id);
		
		List<Player> players = payment.getPlayers();
		player = playerservice.findById(newPlayer.getId());
		
		if (!players.contains(player) || payment == null) {
			players.add(player);
			payment.setPlayers(players);
		} else {
			response.put("message", "Error, el jugador ya tiene este pago.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			payment = paymentservice.save(payment);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		response.put("message", "¡Se han cambiado los datos con éxito!");
		//response.put("payment", newPayment);
		return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{payment_id}/delete/player/{id}")
	public ResponseEntity<?> deletePlayerToPayment(@PathVariable Integer payment_id, @PathVariable Integer id) {
		Player player = null;
		HashMap<String, Object> response = new HashMap<>();
		Payment payment;
		payment = paymentservice.findById(payment_id);
		
		List<Player> players = payment.getPlayers();
		player = playerservice.findById(id);
		
		try {
			players.remove(player);
			payment.setPlayers(players);
			payment = paymentservice.save(payment);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		response.put("message", "¡Se han cambiado los datos con éxito!");
		//response.put("payment", newPayment);
		return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePlayer(@PathVariable Integer id) {
		Payment payment = paymentservice.findById(id);
		HashMap<String, Object> response = new HashMap<>();
		try {
			paymentservice.remove(payment.getId());
		} catch (DataAccessException e) {
			response.put("message", "Error al eliminar el jugador de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "¡El pago fue eliminado con éxito!");
		return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
	}
}
