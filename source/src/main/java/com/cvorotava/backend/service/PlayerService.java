package com.cvorotava.backend.service;

import com.cvorotava.backend.dto.PlayerDto;
import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.error.exception.DeleteOperationException;
import com.cvorotava.backend.error.exception.InternalServerException;
import com.cvorotava.backend.error.exception.NoContentException;
import com.cvorotava.backend.error.exception.NotFoundException;
import com.cvorotava.backend.mapper.IPlayerMapper;
import com.cvorotava.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements IPlayerService {
    private final static String UPLOADED_FOLDER = "src/main/resources/static/images/";
    private final PlayerRepository playerRepository;
    private final IPlayerMapper playerMapper;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, IPlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerDto> findAll() {
        return getUsersOrThrowNoContent(playerRepository.findAll(), "en la BBDD aun");
    }

    @Override
    @Transactional(readOnly = true)
    public PlayerDto findById(Integer id) {
        return getUserOrThrowNotFound(playerRepository.findById(id), "con ese id");
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerDto> findByCategory(String category) {
        return getUsersOrThrowNoContent(playerRepository.findByCategory(category), "con esa categoria");
    }

    @Override
    @Transactional(readOnly = true)
    public PlayerDto findByDni(String dni) {
        return getUserOrThrowNotFound(playerRepository.findByDni(dni), "con ese DNI");
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerDto> searchLike(String search) {
        return getUsersOrThrowNoContent(playerRepository.searchLike(search), "que contengan lo que buscas");
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerDto> findAllOrderedBy(String order) {
        if (playerRepository.findAll().isEmpty()) {
            throw new NoContentException("No existen jugadores en la BBDD aun");
        }
        Sort sorter = switch (order) {
            case "surnames" -> Sort.by("surname1", "surname2", "name");
            case "name" -> Sort.by("name", "surname1", "surname2");
            default -> Sort.by(order);
        };
        return getUsersOrThrowNoContent(playerRepository.findAllOrderedBy(sorter), "aun en la BBDD");
    }

    @Override
    @Transactional(readOnly = true)
    public String[] countPlayers() {
        return new String[]{playerRepository.countFemPlayers(), playerRepository.countMasPlayers()};
    }

    @Override
    @Transactional
    public void delete(PlayerDto playerDto) {
        try {
            playerRepository.delete(playerMapper.playerDTOToEntity(playerDto));
        } catch (Exception e) {
            throw new DeleteOperationException("No se ha podido borrar el jugador de la base de datos", e);
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        try {
            playerRepository.deleteAll();
        } catch (Exception e) {
            throw new DeleteOperationException("No se han podido borrar todos los jugadores de la base de datos", e);
        }
    }

    @Override
    @Transactional
    public PlayerDto save(Player entity) {
        try {
            return playerMapper.playerToDTO(playerRepository.save(entity));
        } catch (Exception e) {
            throw new InternalServerException("No se ha podido insertar el jugador en la base de datos", e);
        }
    }

    @Override
    @Transactional
    public PlayerDto uploadImage(Integer id, MultipartFile file) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new NotFoundException("El jugador proporcionado no existe"));
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
                player.setImage(file.getOriginalFilename());
            }
            return playerMapper.playerToDTO(playerRepository.save(player));
        } catch (Exception e) {
            throw new InternalServerException("La imagen no pudo ser guardada", e);
        }
    }

    private PlayerDto getUserOrThrowNotFound(Optional<Player> player, String message) {
        return playerMapper.playerToDTO(player.orElseThrow(() -> new NotFoundException("No existe el jugador " + message)));
    }

    private List<PlayerDto> getUsersOrThrowNoContent(List<Player> players, String message) {
        if (players.isEmpty()) {
            throw new NoContentException("No existen jugadores " + message);
        }
        return playerMapper.playersListToDTOList(players);
    }
}