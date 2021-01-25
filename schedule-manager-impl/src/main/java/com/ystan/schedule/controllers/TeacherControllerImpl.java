package com.ystan.schedule.controllers;


import com.ystan.schedule.models.TeacherDTO;
import com.ystan.schedule.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedule")
public class TeacherControllerImpl {

    @Autowired
    private TeacherService teacherService;

    @GetMapping(
            path = "/teacher/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TeacherDTO> getById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(teacherService.findById(id), HttpStatus.OK);
    }

    @PostMapping(
            path = "/teacher"
    )
    public ResponseEntity<TeacherDTO> saveOrUpdate(@RequestBody TeacherDTO teacher) {
        return new ResponseEntity<>(teacherService.saveOrUpdate(teacher), HttpStatus.CREATED);
    }
}
