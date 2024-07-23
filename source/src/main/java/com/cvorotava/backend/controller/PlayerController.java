package com.cvorotava.backend.controller;

import com.cvorotava.backend.dto.PlayerDto;
import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200/"})
@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {
    @Autowired
    private PlayerService playerservice;

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getPlayers() {
        return ResponseEntity.ok(playerservice.findAll());
    }

    @GetMapping("/total")
    public ResponseEntity<String[]> getPlayersCount() {
        return ResponseEntity.ok(playerservice.countPlayers());
    }

    @GetMapping("/orderedBy/{order}")
    public ResponseEntity<List<PlayerDto>> getPlayersOrderedBy(@PathVariable String order) {
        return ResponseEntity.ok(playerservice.findAllOrderedBy(order));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<PlayerDto>> getPlayersByCategory(@PathVariable String category) {
        return ResponseEntity.ok(playerservice.findByCategory(category));
    }

    @GetMapping("/search/{search}")
    public ResponseEntity<List<PlayerDto>> searchPlayersBy(@PathVariable String search) {
        if (search.equals("empty")) {
            return getPlayers();
        }
        return ResponseEntity.ok(playerservice.searchLike(search));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<PlayerDto> getPlayerByDni(@PathVariable String dni) {
        return ResponseEntity.ok(playerservice.findByDni(dni));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable Integer id) {
        return ResponseEntity.ok(playerservice.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<PlayerDto> save(@RequestBody Player player) {
        return ResponseEntity.ok(playerservice.save(player));
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<PlayerDto> uploadImage(@RequestParam("id") Integer id, @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(playerservice.uploadImage(id, image));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        playerservice.delete(id);
    }

    @DeleteMapping
    public void deleteAll() {
        playerservice.deleteAll();
    }
}