package com.cvorotava.backend.mapper;

import com.cvorotava.backend.dto.PaymentDto;
import com.cvorotava.backend.entity.Payment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IPaymentMapper {
    PaymentDto paymentToDTO(Payment payment);

    @InheritInverseConfiguration
    Payment paymentDTOToEntity(PaymentDto paymentDTO);

    List<PaymentDto> paymentsListToDTOList(List<Payment> paymentList);

    List<Payment> dtoListToPaymentsList(List<PaymentDto> paymentDtoList);
}
