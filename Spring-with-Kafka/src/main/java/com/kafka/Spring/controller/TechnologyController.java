package com.kafka.Spring.controller;

import com.kafka.Spring.technology.entity.Technology;
import com.kafka.Spring.technology.service.TechnologyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth/technology/")
public class TechnologyController {
    @Autowired
    private TechnologyService technologyService;

    @PostMapping(path = "addTechnologies")
    public ResponseEntity<Boolean> addTechnologies(@RequestBody @NotNull List<Technology> technologies) {

        return new ResponseEntity<>(technologyService.addTechnologies(technologies), HttpStatus.OK);
    }

    @PutMapping(path = "updateTechnology")
    public ResponseEntity<Boolean> updateTechnologies(@RequestBody @NotNull @Valid Technology technologyy) {

        return new ResponseEntity<>(technologyService.updateTechnology(technologyy), HttpStatus.OK);
    }

    @DeleteMapping(path = "deleteTechnology/{technologyId}")
    public ResponseEntity<Boolean> deleteTechnology(@PathVariable @NotNull Integer technologyId) {

        return new ResponseEntity<>(technologyService.deleteTechnology(technologyId), HttpStatus.OK);
    }

    @GetMapping(path = "findAll")
    public ResponseEntity<List<Technology>> findAll() {
        return new ResponseEntity<>(technologyService.findAllTechnology(), HttpStatus.OK);
    }

}
