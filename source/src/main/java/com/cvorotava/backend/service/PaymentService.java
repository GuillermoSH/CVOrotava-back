package com.cvorotava.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cvorotava.backend.entity.Payment;
import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.repository.PaymentRepository;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentrepository;
	
	public Payment save(Payment entity) {
		return paymentrepository.save(entity);
	}

	public Payment findById(Integer id) {
		return paymentrepository.findById(id).get();
	}

	public List<Payment> findAll() {
		return paymentrepository.findAll();
	}

	public Boolean remove(Integer id) {
		Optional<Payment> currentPayment = paymentrepository.findById(id);
		if (currentPayment.isPresent()) {
			paymentrepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public void removeAll() {
		paymentrepository.deleteAll();
	}
	
	public List<Payment> findByConcept(String category) {
		return paymentrepository.findByConcept(category);
	}
	
	public List<Payment> searchLike(String search) {
		return paymentrepository.searchLike(search);
	}
}
