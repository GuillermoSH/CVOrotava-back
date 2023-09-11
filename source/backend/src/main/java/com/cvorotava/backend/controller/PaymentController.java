package com.cvorotava.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvorotava.backend.entity.Payment;
import com.cvorotava.backend.service.PaymentService;

@CrossOrigin(origins = {"http://192.168.1.27:4200/", "http://localhost:4200/"})
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
	@Autowired
	private PaymentService paymentservice;
	
	@GetMapping
	public List<Payment> getPayments() {
		return paymentservice.findAll();
	}
}
