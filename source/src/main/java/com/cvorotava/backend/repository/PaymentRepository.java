package com.cvorotava.backend.repository;

import com.cvorotava.backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Query(value = "SELECT p FROM Payment p ORDER BY p.year, p.month, p.concept")
    List<Payment> findAll();

    List<Payment> findByConcept(String concept);

    @Query(value = "SELECT p FROM Payment p WHERE (p.year = :firstYear AND p.month BETWEEN 9 AND 12) OR (p.year = :secondYear AND p.month BETWEEN 1 AND 8)")
    List<Payment> findBySeason(@Param("firstYear") Integer firstYear, @Param("secondYear") Integer secondYear);

    @Query(value = "SELECT DISTINCT " +
            "CASE WHEN p.month >= 9 THEN " +
            "CONCAT(p.year, '-', p.year + 1) " +
            "ELSE CONCAT(p.year -1, '-', p.year) " +
            "END AS seasons " +
            "FROM Payment p")
    List<String> findAvailableSeasons();

    @Query(value = "SELECT p FROM Payment p WHERE CONCAT_WS(' ', p.quantity, p.month, p.year, p.concept) LIKE %:search%")
    List<Payment> searchLike(@Param("search") String search);
}
