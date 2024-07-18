package com.cvorotava.backend.service;

import com.cvorotava.backend.entity.Config;
import com.cvorotava.backend.entity.Player;
import com.cvorotava.backend.error.exception.InternalServerException;
import com.cvorotava.backend.error.exception.NoContentException;
import com.cvorotava.backend.error.exception.NotFoundException;
import com.cvorotava.backend.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigService implements IConfigService {
    @Autowired
    private ConfigRepository configurationrepository;

    @Override
    public List<Config> findAll() {
        return getUsersOrThrowNoContent(configurationrepository.findAll(), "en la BBDD aun");
    }

    @Override
    public void delete() {
        try {
            configurationrepository.delete(findAll().get(0));
        } catch (Exception e) {
            throw new NotFoundException("No hay ninguna configuracion que borrar");
        }
    }

    @Override
    public Config save(Config entity) {
        try {
            return configurationrepository.save(entity);
        } catch (Exception e) {
            throw new InternalServerException("No se ha podido guardar la configuracion");
        }
    }

    private Config getUserOrThrowNotFound(Optional<Config> config, String message) {
        return config.orElseThrow(() -> new NotFoundException("No existe la configuracion " + message));
    }

    private List<Config> getUsersOrThrowNoContent(List<Config> configs, String message) {
        if (configs.isEmpty()) {
            throw new NoContentException("No existen configuraciones " + message);
        }
        return configs;
    }
}
