package com.cvorotava.backend.service;

import com.cvorotava.backend.dto.EquipmentDto;
import com.cvorotava.backend.entity.Equipment;
import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.error.exception.DeleteOperationException;
import com.cvorotava.backend.error.exception.InternalServerException;
import com.cvorotava.backend.error.exception.NoContentException;
import com.cvorotava.backend.error.exception.NotFoundException;
import com.cvorotava.backend.mapper.IEquipmentMapper;
import com.cvorotava.backend.repository.EquipmentRepository;
import com.cvorotava.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService implements IEquipmentService {
    private final IEquipmentMapper equipmentMapper;
    private final EquipmentRepository equipmentRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository, IEquipmentMapper equipmentMapper, PlayerRepository playerRepository) {
        this.equipmentMapper = equipmentMapper;
        this.equipmentRepository = equipmentRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public List<EquipmentDto> findAll() {
        return getUsersOrThrowNoContent(equipmentRepository.findAll(), "en la BBDD aun");
    }

    @Override
    public EquipmentDto findById(Integer id) {
        return getUserOrThrowNotFound(equipmentRepository.findById(id), "con ese id");
    }

    @Override
    public List<EquipmentDto> searchLike(String search) {
        return getUsersOrThrowNoContent(equipmentRepository.searchLike(search), "que contengan lo que buscas");
    }

    @Override
    public EquipmentDto save(Equipment entity) {
        try {
            return equipmentMapper.equipmentToDTO(equipmentRepository.save(entity));
        } catch (Exception e) {
            throw new InternalServerException("No se pudo guardar la equipacion en la BBDD", e);
        }
    }

    @Override
    public EquipmentDto dropPlayerFromEquipment(Integer equipment_id, Integer player_id) {
        try {
            Equipment entity = equipmentRepository.findById(equipment_id).orElseThrow(() -> new NotFoundException("No existe un equipaje con ese id"));
            Player playerToDrop = playerRepository.findById(player_id).orElseThrow(() -> new NotFoundException("No existe el jugador con ese id"));
            List<Player> players = entity.getPlayers();
            players.remove(playerToDrop);
            entity.setPlayers(players);
            return equipmentMapper.equipmentToDTO(equipmentRepository.save(entity));
        } catch (Exception e) {
            throw new DeleteOperationException("No se pudo guardar la equipacion en la BBDD", e);
        }
    }

    @Override
    public void delete(EquipmentDto equipmentDto) {
        try {
            equipmentRepository.delete(equipmentMapper.equipmentDTOToEntity(equipmentDto));
        } catch (Exception e) {
            throw new DeleteOperationException("No se pudo borrar la equipacion de la BBDD", e);
        }
    }

    private EquipmentDto getUserOrThrowNotFound(Optional<Equipment> equipment, String message) {
        return equipmentMapper.equipmentToDTO(equipment.orElseThrow(() -> new NotFoundException("No existe la equipacion " + message)));
    }

    private List<EquipmentDto> getUsersOrThrowNoContent(List<Equipment> equipments, String message) {
        if (equipments.isEmpty()) {
            throw new NoContentException("No existen equipaciones " + message);
        }
        return equipmentMapper.equipmentListToDTOList(equipments);
    }
}
