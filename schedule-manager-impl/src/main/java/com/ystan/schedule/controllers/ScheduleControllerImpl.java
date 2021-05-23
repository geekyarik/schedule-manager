package com.ystan.schedule.controllers;

import com.ystan.schedule.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("schedule")
public class ScheduleControllerImpl {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping(
            path = "/generate",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity generate(@PathVariable(name = "schoolId") String id) {
        scheduleService.generateSchedule(id);

        return new ResponseEntity(id, HttpStatus.OK);
    }
}
