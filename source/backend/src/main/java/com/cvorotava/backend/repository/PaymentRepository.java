package com.cvorotava.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cvorotava.backend.entity.Payment;
import com.cvorotava.backend.entity.Player;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	List<Payment> findByConcept(String concept);
	
	@Query(value="SELECT p FROM Payment p WHERE CONCAT_WS(' ', p.quantity, p.month, p.year, p.concept) LIKE %:search%")
	List<Payment> searchLike(@Param("search") String search);
}
