package com.kafka.Spring.controller;


import com.kafka.Spring.mission.entity.Mission;

import com.kafka.Spring.mission.service.MissionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.ListSelectionEvent;
import java.util.List;

@RestController
@RequestMapping("api/auth/mission/")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @GetMapping(path = "{missionId}")
    public ResponseEntity<Mission> getMission(@PathVariable @NotNull Integer missionId) {
        return new ResponseEntity<>(missionService.getMission(missionId), HttpStatus.OK);

    }

    @PostMapping(path = "saveMission")
    public ResponseEntity<Boolean> addMission(@RequestBody  @NotNull @Valid Mission mission) {
        return new ResponseEntity<>(missionService.addMission(mission), HttpStatus.OK);

    }

    @DeleteMapping(path = "deleteMission/{missionId}")
    public ResponseEntity<Boolean> deleteMission(@PathVariable @NotNull Integer missionId) {
        return new ResponseEntity<>(missionService.deleteMission(missionId), HttpStatus.OK);

    }


    @GetMapping(path = "findAll")
    public ResponseEntity<List<Mission>> findAllMission() {
        //TODO associé la mission a l'utilisateur list de mission par user
        return new ResponseEntity<>(missionService.findAllMissions(), HttpStatus.OK);

    }


}
