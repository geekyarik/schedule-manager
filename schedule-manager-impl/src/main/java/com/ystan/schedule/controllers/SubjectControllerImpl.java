package com.ystan.schedule.controllers;


import com.ystan.schedule.models.SubjectDTO;
import com.ystan.schedule.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedule")
public class SubjectControllerImpl {

    @Autowired
    private SubjectService subjectService;

    @GetMapping(
            path = "/subject/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<SubjectDTO> getById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(subjectService.findById(id), HttpStatus.OK);
    }

    @PostMapping(
            path = "/subject"
    )
    public ResponseEntity<SubjectDTO> saveOrUpdate(@RequestBody SubjectDTO subject) {
        return new ResponseEntity<>(subjectService.saveOrUpdate(subject), HttpStatus.CREATED);
    }
}
