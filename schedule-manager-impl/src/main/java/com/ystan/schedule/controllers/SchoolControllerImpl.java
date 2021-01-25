package com.ystan.schedule.controllers;


import com.ystan.schedule.models.SchoolDTO;
import com.ystan.schedule.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedule")
public class SchoolControllerImpl {

    @Autowired
    private SchoolService schoolService;

    @GetMapping(
            path = "/school/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<SchoolDTO> getById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(schoolService.findById(id), HttpStatus.OK);
    }

    @PostMapping(
            path = "/school"
    )
    public ResponseEntity<SchoolDTO> saveOrUpdate(@RequestBody SchoolDTO school) {
        return new ResponseEntity<>(schoolService.saveOrUpdate(school), HttpStatus.CREATED);
    }
}
