package com.cvorotava.backend.mapper;

import com.cvorotava.backend.dto.PlayerDto;
import com.cvorotava.backend.entity.Player;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IPlayerMapper {
    PlayerDto playerToDTO(Player player);

    @InheritInverseConfiguration
    Player playerDTOToEntity(PlayerDto playerDTO);

    List<PlayerDto> playersListToDTOList(List<Player> playerList);

    List<Player> dtoListToPlayersList(List<PlayerDto> playerDTOList);
}
