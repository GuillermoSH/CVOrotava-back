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

import com.cvorotava.backend.entity.Payment;
import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.service.PaymentService;

@CrossOrigin(origins = {"http://192.168.1.27:4200/", "http://localhost:4200/", "https://zm220cwj-4200.euw.devtunnels.ms/"})
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
	@Autowired
	private PaymentService paymentservice;
	
	@GetMapping
	public List<Payment> getPayments() {
		return paymentservice.findAll();
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
	public ResponseEntity<?> updatePlayer(@RequestBody Payment payment) {
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
