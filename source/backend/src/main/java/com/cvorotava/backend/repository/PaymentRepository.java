package com.cvorotava.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cvorotava.backend.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	@Query(value="SELECT p FROM Payment p ORDER BY p.year, p.month, p.concept")
	List<Payment> findAll();
	List<Payment> findByConcept(String concept);
	Optional<Payment> findById(Integer id);
	
	@Query(value="SELECT p FROM Payment p WHERE CONCAT_WS(' ', p.quantity, p.month, p.year, p.concept) LIKE %:search%")
	List<Payment> searchLike(@Param("search") String search);
}
