package com.cvorotava.backend.service;

import com.cvorotava.backend.entity.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPlayerService {
    List<Player> findAll();

    Player findById(Integer id);

    List<Player> findByCategory(String category);

    Player findByDni(String dni);

    List<Player> searchLike(String search);

    List<Player> findAllOrderedBy(String order);

    String[] countPlayers();

    void delete(Integer id);

    void deleteAll();

    Player save(Player entity);
}
