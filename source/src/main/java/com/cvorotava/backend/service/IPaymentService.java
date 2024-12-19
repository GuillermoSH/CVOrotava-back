package com.cvorotava.backend.service;

import com.cvorotava.backend.dto.PaymentDto;
import com.cvorotava.backend.dto.PlayerDto;
import com.cvorotava.backend.entity.Payment;
import com.cvorotava.backend.entity.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IPaymentService {
    List<PaymentDto> findAll();

    PaymentDto findById(Integer id);

    List<PaymentDto> findByMonth(String month, String year);

    List<PaymentDto> findByConcept(String concept);

    List<PaymentDto> searchLike(String search);

    List<PaymentDto> findBySeason(String season);

    List<String> findAvailableSeasons();

    PaymentDto assignPlayerToPayment(Integer paymentId, Player player);

    List<PlayerDto> findAllDefaultersFromPayment(Integer id);

    List<PlayerDto> findAllDefaultersByMonth(String month, String year);

    PaymentDto save(Payment entity);

    PaymentDto dropPlayerFromPayment(Integer paymentId, Integer playerId);

    void delete(PaymentDto paymentDto);
}
