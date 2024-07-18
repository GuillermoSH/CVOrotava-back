package com.cvorotava.backend.controller;

import com.cvorotava.backend.entity.Config;
import com.cvorotava.backend.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200/"})
@RestController
@RequestMapping("/api/v1/configurations")
public class ConfigController {
    @Autowired
    private ConfigService configservice;

    @GetMapping
    public ResponseEntity<Config> getConfigurations() {
        return ResponseEntity.ok(configservice.findAll().get(0));
    }

    @PostMapping("/save")
    public ResponseEntity<Config> save(@RequestBody Config configuration) {
        return ResponseEntity.ok(configservice.save(configuration));
    }

    @DeleteMapping()
    public void delete() {
        configservice.delete();
    }
}
