package com.cvorotava.backend.mapper;

import com.cvorotava.backend.dto.PlayerDto;
import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.error.exception.NotFoundException;
import com.cvorotava.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class PlayerMapperImpl implements IPlayerMapper {
    @Override
    public PlayerDto playerToDTO(Player player) {
        if (player == null) {
            return null;
        }

        return PlayerDto.builder()
                .id(player.getId())
                .dni(player.getDni())
                .name(player.getName())
                .surname1(player.getSurname1())
                .surname2(player.getSurname2())
                .telephone(player.getTelephone())
                .email(player.getEmail())
                .address(player.getAddress())
                .birthday(player.getBirthday())
                .category(player.getCategory())
                .image(player.getImage())
                .build();
    }

    @Override
    public Player playerDTOToEntity(PlayerDto playerDTO) {
        if (playerDTO == null) {
            return null;
        }

        return Player.builder()
                .id(playerDTO.getId())
                .dni(playerDTO.getDni())
                .name(playerDTO.getName())
                .surname1(playerDTO.getSurname1())
                .surname2(playerDTO.getSurname2())
                .telephone(playerDTO.getTelephone())
                .email(playerDTO.getEmail())
                .address(playerDTO.getAddress())
                .birthday(playerDTO.getBirthday())
                .category(playerDTO.getCategory())
                .image(playerDTO.getImage())
                .build();
    }

    @Override
    public List<PlayerDto> playersListToDTOList(List<Player> playerList) {
        if (playerList.isEmpty()) {
            return Collections.emptyList();
        }

        List<PlayerDto> playerDtoList = new ArrayList<>();

        for (Player player : playerList) {
            playerDtoList.add(playerToDTO(player));
        }

        return playerDtoList;
    }

    @Override
    public List<Player> dtoListToPlayersList(List<PlayerDto> playerDTOList) {
        if (playerDTOList.isEmpty()) {
            return Collections.emptyList();
        }

        List<Player> playerList = new ArrayList<>();

        for (PlayerDto playerDto : playerDTOList) {
            playerList.add(playerDTOToEntity(playerDto));
        }

        return playerList;
    }
}
