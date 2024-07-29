package com.cvorotava.backend.service;

import com.cvorotava.backend.dto.PaymentDto;
import com.cvorotava.backend.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPaymentService {
    List<PaymentDto> findAll();

    PaymentDto findById(Integer id);

    List<PaymentDto> findByConcept(String concept);

    List<PaymentDto> searchLike(String search);

    List<PaymentDto> findBySeason(String season);

    PaymentDto save(Payment entity);

    PaymentDto dropPlayerFromPayment(Integer payment_id, Integer player_id);

    void delete(PaymentDto paymentDto);
}
