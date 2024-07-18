package com.cvorotava.backend.service;

import java.util.List;
import java.util.Optional;

import com.cvorotava.backend.entity.Config;
import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.error.exception.InternalServerException;
import com.cvorotava.backend.error.exception.NoContentException;
import com.cvorotava.backend.error.exception.NotFoundException;
import com.cvorotava.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvorotava.backend.entity.Equipment;
import com.cvorotava.backend.repository.EquipmentRepository;

@Service
public class EquipmentService implements IEquipmentService {
    @Autowired
    private EquipmentRepository equipementrepository;

    @Autowired
    private PlayerRepository playerrepository;

    @Override
    public List<Equipment> findAll() {
        return getUsersOrThrowNoContent(equipementrepository.findAll(), "en la BBDD aun");
    }

    @Override
    public Equipment findById(Integer id) {
        return getUserOrThrowNotFound(equipementrepository.findById(id), "con ese id");
    }

    @Override
    public List<Equipment> searchLike(String search) {
        return getUsersOrThrowNoContent(equipementrepository.searchLike(search), "que contengan lo que buscas");
    }

    @Override
    public Equipment save(Equipment entity) {
        try {
            return equipementrepository.save(entity);
        } catch (Exception e) {
            throw new InternalServerException("No se pudo guardar la equipacion en la BBDD");
        }
    }

    @Override
    public Equipment dropPlayerFromEquipment(Integer equipment_id, Integer player_id) {
        try {
            Equipment entity = findById(equipment_id);
            Player playerToDrop = playerrepository.findById(player_id).get();
            List<Player> players = entity.getPlayers();
            players.remove(playerToDrop);
            entity.setPlayers(players);
            return equipementrepository.save(entity);
        } catch (Exception e) {
            throw new InternalServerException("No se pudo guardar la equipacion en la BBDD");
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            equipementrepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerException("No se pudo borrar la equipacion de la BBDD");
        }
    }

    private Equipment getUserOrThrowNotFound(Optional<Equipment> equipment, String message) {
        return equipment.orElseThrow(() -> new NotFoundException("No existe la equipacion " + message));
    }

    private List<Equipment> getUsersOrThrowNoContent(List<Equipment> equipments, String message) {
        if (equipments.isEmpty()) {
            throw new NoContentException("No existen equipaciones " + message);
        }
        return equipments;
    }
}
