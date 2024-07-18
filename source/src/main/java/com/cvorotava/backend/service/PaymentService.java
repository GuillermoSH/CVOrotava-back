package com.cvorotava.backend.service;

import java.util.List;
import java.util.Optional;

import com.cvorotava.backend.entity.Equipment;
import com.cvorotava.backend.error.exception.InternalServerException;
import com.cvorotava.backend.error.exception.NoContentException;
import com.cvorotava.backend.error.exception.NotFoundException;
import com.cvorotava.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cvorotava.backend.entity.Payment;
import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.repository.PaymentRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService implements IPaymentService {
	@Autowired
	private PaymentRepository paymentrepository;

	@Autowired
	private PlayerRepository playerrepository;

	@Override
	@Transactional(readOnly = true)
	public List<Payment> findAll() {
		return getPaymentOrThrowNoContent(paymentrepository.findAll(), "en la BBDD aun");
	}

	@Override
	@Transactional(readOnly = true)
	public Payment findById(Integer id) {
		return getPaymentOrThrowNotFound(paymentrepository.findById(id), "con ese id");
	}

	@Override
	@Transactional(readOnly = true)
	public Payment findByConcept(String concept) {
		return getPaymentOrThrowNotFound(paymentrepository.findByConcept(concept), "con ese concepto");
	}

	@Override
	@Transactional(readOnly = true)
	public List<Payment> searchLike(String search) {
		return getPaymentOrThrowNoContent(paymentrepository.searchLike(search), "con los parametros que buscas");
	}

	@Override
	@Transactional
	public Payment save(Payment entity) {
		try {
			return paymentrepository.save(entity);
		} catch (Exception e) {
			throw new InternalServerException("No se pudo guardar el pago en la BBDD");
		}
	}

	@Override
	@Transactional
	public Payment dropPlayerFromPayment(Integer payment_id, Integer player_id) {
		try {
			Payment entity = findById(payment_id);
			Player playerToDrop = playerrepository.findById(player_id).get();
			List<Player> players = entity.getPlayers();
			players.remove(playerToDrop);
			entity.setPlayers(players);
			return paymentrepository.save(entity);
		} catch (Exception e) {
			throw new InternalServerException("No se pudo guardar el pago en la BBDD");
		}
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		try {
			paymentrepository.deleteById(id);
		} catch (Exception e) {
			throw new InternalServerException("No se pudo borrar el pago de la BBDD");
		}
	}

	private Payment getPaymentOrThrowNotFound(Optional<Payment> payment, String message) {
		return payment.orElseThrow(() -> new NotFoundException("No existe el jugador " + message));
	}

	private List<Payment> getPaymentOrThrowNoContent(List<Payment> payments, String message) {
		if (payments.isEmpty()) {
			throw new NoContentException("No existen jugadores " + message);
		}
		return payments;
	}
}
