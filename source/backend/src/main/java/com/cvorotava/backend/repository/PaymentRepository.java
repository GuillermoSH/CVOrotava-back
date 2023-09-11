package com.cvorotava.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvorotava.backend.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	List<Payment> findAll();
	List<Payment> findByConcept(String concept);
}
