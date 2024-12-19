package com.cvorotava.backend.controller;

import java.util.List;

import com.cvorotava.backend.dto.PaymentDto;
import com.cvorotava.backend.dto.PlayerDto;
import com.cvorotava.backend.entity.Player;
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

	@GetMapping("/season/{season}")
	public ResponseEntity<List<PaymentDto>> getPaymentsBySeason(@PathVariable String season) {
		return ResponseEntity.ok(paymentservice.findBySeason(season));
	}

	@GetMapping("/seasons")
	public ResponseEntity<List<String>> getAvailableSeason() {
		return ResponseEntity.ok(paymentservice.findAvailableSeasons());
	}

	@GetMapping("/{id}/defaulters")
	public ResponseEntity<List<PlayerDto>> getPaymentDefaulters(@PathVariable Integer id) {
		return ResponseEntity.ok(paymentservice.findAllDefaultersFromPayment(id));
	}

	@GetMapping("/{year}/{month}/defaulters")
	public ResponseEntity<List<PlayerDto>> getPaymentDefaultersByMonth(@PathVariable String month, @PathVariable String year) {
		return ResponseEntity.ok(paymentservice.findAllDefaultersByMonth(month, year));
	}

	@PostMapping("/save")
	public ResponseEntity<PaymentDto> save(@Valid @RequestBody Payment equipment) {
		return ResponseEntity.ok(paymentservice.save(equipment));
	}

	@PostMapping("/{paymentId}/save/player/{player}")
	public ResponseEntity<PaymentDto> assignPlayerFromPayment(@PathVariable Integer paymentId, @PathVariable Player player) {
		return ResponseEntity.ok(paymentservice.assignPlayerToPayment(paymentId, player));
	}

	@DeleteMapping("/{paymentId}/delete/player/{playerId}")
	public ResponseEntity<PaymentDto> dropPlayerFromPayment(@PathVariable Integer paymentId, @PathVariable Integer playerId) {
		return ResponseEntity.ok(paymentservice.dropPlayerFromPayment(paymentId, playerId));
	}

	@DeleteMapping("/delete")
	public void delete(@RequestBody PaymentDto paymentDto) {
		paymentservice.delete(paymentDto);
	}
}
