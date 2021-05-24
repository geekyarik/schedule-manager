package com.ystan.schedule.controllers;

import com.ystan.schedule.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedule")
public class ScheduleControllerImpl {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(
            path = "/generate",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity generate(@RequestParam(name = "schoolId") String schoolId) {
        scheduleService.generateSchedule(schoolId);

        return new ResponseEntity(schoolId, HttpStatus.OK);
    }
}
