package com.cvorotava.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvorotava.backend.entity.Equipement;
import com.cvorotava.backend.entity.Payment;
import com.cvorotava.backend.repository.EquipementRepository;

@Service
public class EquipementService {
	@Autowired
	private EquipementRepository equipementrepository;
	
	public Equipement save(Equipement entity) {
		return equipementrepository.save(entity);
	}

	public Equipement findById(Integer id) {
		return equipementrepository.findById(id).get();
	}

	public List<Equipement> findAll() {
		return equipementrepository.findAll();
	}

	public Boolean remove(Integer id) {
		Optional<Equipement> currentPayment = equipementrepository.findById(id);
		if (currentPayment.isPresent()) {
			equipementrepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public void removeAll() {
		equipementrepository.deleteAll();
	}
	
	public List<Equipement> searchLike(String search) {
		return equipementrepository.searchLike(search);
	}
}
