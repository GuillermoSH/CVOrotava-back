package com.cvorotava.backend.controller;

import java.util.List;

import com.cvorotava.backend.dto.PaymentDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvorotava.backend.entity.Payment;
import com.cvorotava.backend.service.PaymentService;

@CrossOrigin(origins = {"http://localhost:4200/"})
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
	@Autowired
	private PaymentService paymentservice;

	@GetMapping
	public ResponseEntity<List<PaymentDto>> getPayments() {
		return ResponseEntity.ok(paymentservice.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Integer id) {
		return ResponseEntity.ok(paymentservice.findById(id));
	}

	@GetMapping("/concept/{concept}")
	public ResponseEntity<List<PaymentDto>> getPaymentByConcept(@PathVariable String concept) {
		return ResponseEntity.ok(paymentservice.findByConcept(concept));
	}

	@GetMapping("/search/{search}")
	public ResponseEntity<List<PaymentDto>> searchPaymentsBy(@PathVariable String search) {
		return ResponseEntity.ok(paymentservice.searchLike(search));
	}

	@PostMapping("/save")
	public ResponseEntity<PaymentDto> save(@Valid @RequestBody Payment equipment) {
		return ResponseEntity.ok(paymentservice.save(equipment));
	}

	@DeleteMapping("/{payment_id}/delete/player/{player_id}")
	public ResponseEntity<PaymentDto> dropPlayerFromPayment(@PathVariable Integer payment_id, @PathVariable Integer player_id) {
		return ResponseEntity.ok(paymentservice.dropPlayerFromPayment(payment_id, player_id));
	}

	@DeleteMapping("/delete")
	public void delete(@RequestBody PaymentDto paymentDto) {
		paymentservice.delete(paymentDto);
	}
}
