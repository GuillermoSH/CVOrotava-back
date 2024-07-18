package com.cvorotava.backend.service;

import com.cvorotava.backend.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPaymentService {
    List<Payment> findAll();

    Payment findById(Integer id);

    List<Payment> findByConcept(String concept);

    List<Payment> searchLike(String search);

    Payment save(Payment entity);

    Payment dropPlayerFromPayment(Integer payment_id, Integer player_id);

    void delete(Integer id);
}
