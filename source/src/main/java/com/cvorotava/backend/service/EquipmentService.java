package com.cvorotava.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvorotava.backend.entity.Equipment;
import com.cvorotava.backend.repository.EquipmentRepository;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipementrepository;

    public Equipment save(Equipment entity) {
        return equipementrepository.save(entity);
    }

    public Equipment findById(Integer id) {
        return equipementrepository.findById(id).get();
    }

    public List<Equipment> findAll() {
        return equipementrepository.findAll();
    }

    public Boolean remove(Integer id) {
        Optional<Equipment> currentPayment = equipementrepository.findById(id);
        if (currentPayment.isPresent()) {
            equipementrepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void removeAll() {
        equipementrepository.deleteAll();
    }

    public List<Equipment> searchLike(String search) {
        return equipementrepository.searchLike(search);
    }
}
