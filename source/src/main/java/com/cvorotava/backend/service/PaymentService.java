package com.cvorotava.backend.service;

import com.cvorotava.backend.dto.PaymentDto;
import com.cvorotava.backend.entity.Payment;
import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.error.exception.DeleteOperationException;
import com.cvorotava.backend.error.exception.InternalServerException;
import com.cvorotava.backend.error.exception.NoContentException;
import com.cvorotava.backend.error.exception.NotFoundException;
import com.cvorotava.backend.mapper.IPaymentMapper;
import com.cvorotava.backend.repository.PaymentRepository;
import com.cvorotava.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements IPaymentService {
    private final IPaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PlayerRepository playerRepository, IPaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.playerRepository = playerRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDto> findAll() {
        return getPaymentOrThrowNoContent(paymentRepository.findAll(), "en la BBDD aun");
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentDto findById(Integer id) {
        return getPaymentOrThrowNotFound(paymentRepository.findById(id), "con ese id");
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDto> findBySeason(String season) {
        int firstYear = Integer.parseInt(season.split("-")[0]);
        int secondYear = Integer.parseInt(season.split("-")[1]);
        return getPaymentOrThrowNoContent(paymentRepository.findBySeason(firstYear, secondYear), "en esa temporada");
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAvailableSeasons() {
        return paymentRepository.findAvailableSeasons();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDto> findByConcept(String concept) {
        return getPaymentOrThrowNoContent(paymentRepository.findByConcept(concept), "con ese concepto");
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDto> searchLike(String search) {
        return getPaymentOrThrowNoContent(paymentRepository.searchLike(search), "con los parametros que buscas");
    }

    @Override
    @Transactional
    public PaymentDto save(Payment entity) {
        try {
            return paymentMapper.paymentToDTO(paymentRepository.save(entity));
        } catch (Exception e) {
            throw new InternalServerException("No se pudo guardar el pago en la BBDD", e);
        }
    }

    @Override
    @Transactional
    public PaymentDto dropPlayerFromPayment(Integer paymentId, Integer playerId) {
        try {
            Payment entity = paymentRepository.findById(paymentId).orElseThrow(() -> new NotFoundException("No existe un pago con ese id"));
            Player playerToDrop = playerRepository.findById(playerId).orElseThrow(() -> new NotFoundException("No existe el jugador con ese id"));
            List<Player> players = entity.getPlayers();
            players.remove(playerToDrop);
            entity.setPlayers(players);
            return paymentMapper.paymentToDTO(paymentRepository.save(entity));
        } catch (Exception e) {
            throw new DeleteOperationException("No se pudo guardar el pago en la BBDD", e);
        }
    }

    @Override
    @Transactional
    public void delete(PaymentDto paymentDto) {
        try {
            paymentRepository.delete(paymentMapper.paymentDTOToEntity(paymentDto));
        } catch (Exception e) {
            throw new DeleteOperationException("No se pudo borrar el pago de la BBDD", e);
        }
    }

    private PaymentDto getPaymentOrThrowNotFound(Optional<Payment> payment, String message) {
        return paymentMapper.paymentToDTO(payment.orElseThrow(() -> new NotFoundException("No existe el pago " + message)));
    }

    private List<PaymentDto> getPaymentOrThrowNoContent(List<Payment> payments, String message) {
        if (payments.isEmpty()) {
            throw new NoContentException("No existen pagos " + message);
        }
        return paymentMapper.paymentsListToDTOList(payments);
    }
}
