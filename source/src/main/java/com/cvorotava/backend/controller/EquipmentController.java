package com.cvorotava.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cvorotava.backend.dto.EquipmentDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvorotava.backend.entity.Equipment;
import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.service.EquipmentService;
import com.cvorotava.backend.service.PlayerService;

@CrossOrigin(origins = {"http://localhost:4200/"})
@RestController
@RequestMapping("/api/v1/equipments")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<EquipmentDto>> getEquipments() {
        return ResponseEntity.ok(equipmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDto> getEquipmentById(@PathVariable Integer id) {
        return ResponseEntity.ok(equipmentService.findById(id));
    }

    @GetMapping("/search/{search}")
    public ResponseEntity<List<EquipmentDto>> searchEquipmentsBy(@PathVariable String search) {
        return ResponseEntity.ok(equipmentService.searchLike(search));
    }

    @PostMapping("/save")
    public ResponseEntity<EquipmentDto> save(@Valid @RequestBody Equipment equipment) {
        return ResponseEntity.ok(equipmentService.save(equipment));
    }

    @DeleteMapping("/{equipment_id}/delete/player/{player_id}")
    public ResponseEntity<EquipmentDto> dropPlayerFromEquipment(@PathVariable Integer equipment_id, @PathVariable Integer player_id) {
        return ResponseEntity.ok(equipmentService.dropPlayerFromEquipment(equipment_id, player_id));
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody EquipmentDto equipmentDto) {
        equipmentService.delete(equipmentDto);
    }
}
