package com.cvorotava.backend.mapper;

import com.cvorotava.backend.dto.PaymentDto;
import com.cvorotava.backend.entity.Payment;
import com.cvorotava.backend.error.exception.NotFoundException;
import com.cvorotava.backend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PaymentMapperImpl implements IPaymentMapper {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentMapperImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDto paymentToDTO(Payment payment) {
        if (payment == null) {
            return null;
        }

        return PaymentDto.builder()
                .id(payment.getId())
                .quantity(payment.getQuantity())
                .month(payment.getMonth())
                .year(payment.getYear())
                .concept(payment.getConcept())
                .players(payment.getPlayers())
                .build();
    }

    @Override
    public Payment paymentDTOToEntity(PaymentDto paymentDTO) {
        if (paymentDTO == null) {
            return null;
        }

        Payment payment = paymentRepository.findById(paymentDTO.getId()).orElseThrow(() -> new NotFoundException("That payment couldn't be found"));

        return Payment.builder()
                .id(payment.getId())
                .quantity(paymentDTO.getQuantity())
                .month(paymentDTO.getMonth())
                .year(paymentDTO.getYear())
                .concept(paymentDTO.getConcept())
                .players(paymentDTO.getPlayers())
                .build();
    }

    @Override
    public List<PaymentDto> paymentsListToDTOList(List<Payment> paymentList) {
        if (paymentList.isEmpty()) {
            return Collections.emptyList();
        }

        List<PaymentDto> paymentDtoList = new ArrayList<>();

        for (Payment payment : paymentList) {
            paymentDtoList.add(paymentToDTO(payment));
        }

        return paymentDtoList;
    }

    @Override
    public List<Payment> dtoListToPaymentsList(List<PaymentDto> paymentDtoList) {
        if (paymentDtoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<Payment> paymentList = new ArrayList<>();

        for (PaymentDto paymentDto : paymentDtoList) {
            paymentList.add(paymentDTOToEntity(paymentDto));
        }

        return paymentList;
    }
}
