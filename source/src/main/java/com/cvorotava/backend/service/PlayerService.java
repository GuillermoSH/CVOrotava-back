package com.cvorotava.backend.service;

import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.entity.User;
import com.cvorotava.backend.error.exception.InternalServerException;
import com.cvorotava.backend.error.exception.NoContentException;
import com.cvorotava.backend.error.exception.NotFoundException;
import com.cvorotava.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements IPlayerService {
    private static String UPLOADED_FOLDER = "src/main/resources/static/images/";
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Player> findAll() {
        return getUsersOrThrowNoContent(playerRepository.findAll(), "en la BBDD aun");
    }

    @Override
    @Transactional(readOnly = true)
    public Player findById(Integer id) {
        return getUserOrThrowNotFound(playerRepository.findById(id), "con ese id");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> findByCategory(String category) {
        return getUsersOrThrowNoContent(playerRepository.findByCategory(category), "con esa categoria");
    }

    @Override
    @Transactional(readOnly = true)
    public Player findByDni(String dni) {
        return getUserOrThrowNotFound(playerRepository.findByDni(dni), "con ese DNI");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> searchLike(String search) {
        return getUsersOrThrowNoContent(playerRepository.searchLike(search), "que contengan lo que buscas");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> findAllOrderedBy(String order) {
        if (playerRepository.findAll().isEmpty()) {
            throw new NoContentException("No existen jugadores en la BBDD aun");
        }
        Sort sorter;
        switch (order) {
            case "surnames":
                sorter = Sort.by("surname1", "surname2", "name");
                break;
            case "name":
                sorter = Sort.by("name", "surname1", "surname2");
                break;
            default:
                sorter = Sort.by(order);
                break;
        }
        return playerRepository.findAllOrderedBy(sorter);
    }

	@Override
	@Transactional(readOnly = true)
	public String[] countPlayers() {
        return new String[]{playerRepository.countFemPlayers(), playerRepository.countMasPlayers()};
	}

    @Override
    @Transactional
    public void delete(Integer id) {
        try {
            playerRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerException("No se ha podido borrar el jugador de la base de datos");
        }
    }

	@Override
	@Transactional
	public void deleteAll() {
		try {
			playerRepository.deleteAll();
		} catch (Exception e) {
			throw new InternalServerException("No se han podido borrar todos los jugadores de la base de datos");
		}
	}

    @Override
    @Transactional
    public Player save(Player entity) {
        try {
            return playerRepository.save(entity);
        } catch (Exception e) {
            throw new InternalServerException("No se ha podido insertar el jugador en la base de datos");
        }
    }

    @Override
    @Transactional
    public Player uploadImage(Integer id, MultipartFile file) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new NotFoundException("El jugador proporcionado no existe"));
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
                player.setImage(file.getOriginalFilename());
            }
            return playerRepository.save(player);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    private Player getUserOrThrowNotFound(Optional<Player> player, String message) {
        return player.orElseThrow(() -> new NotFoundException("No existe el jugador " + message));
    }

    private List<Player> getUsersOrThrowNoContent(List<Player> players, String message) {
        if (players.isEmpty()) {
            throw new NoContentException("No existen jugadores " + message);
        }
        return players;
    }
}