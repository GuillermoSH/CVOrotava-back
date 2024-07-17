package com.cvorotava.backend.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvorotava.backend.entity.Config;
import com.cvorotava.backend.service.ConfigService;

@CrossOrigin(origins = {"http://192.168.1.27:4200/", "http://localhost:4200/", "https://zm220cwj-4200.euw.devtunnels.ms/", "https://c24djzb4-4200.uks1.devtunnels.ms/"})
@RestController
@RequestMapping("/api/v1/config")
public class ConfigController {
    @Autowired
    private ConfigService configservice;

    @GetMapping
    public List<Config> getConfigurations() {
        return configservice.findAll();
    }

    @PutMapping
    public ResponseEntity<?> updateConfiguration(@RequestBody Config configuration) {
        Config newPayment;
        HashMap<String, Object> response = new HashMap<>();
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(currentDate);
        configuration.setMod_date(date);

        try {
            newPayment = configservice.save(configuration);
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "¡Se han cambiado los datos con éxito!");
        response.put("configuration", newPayment);
        return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteConfiguration() {
        Config configuration = configservice.findAll().get(0);
        HashMap<String, Object> response = new HashMap<>();
        try {
            configservice.remove(configuration.getId());
        } catch (DataAccessException e) {
            response.put("message", "Error al eliminar la equipación de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "¡La configuración fue eliminada con éxito!");
        return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
    }
}
