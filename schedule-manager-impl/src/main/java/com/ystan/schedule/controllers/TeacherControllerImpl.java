package com.ystan.schedule.controllers;


import com.ystan.schedule.models.TeacherDTO;
import com.ystan.schedule.requests.AddSubjectToTeacherRequest;
import com.ystan.schedule.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(
            path = "/teacher",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TeacherDTO>> getBySchoolId(@RequestParam(name = "schoolId") String schoolId) {
        return new ResponseEntity<>(teacherService.findBySchoolId(schoolId), HttpStatus.OK);
    }

    @PostMapping(
            path = "/teacher/addSubject"
    )
    public ResponseEntity<TeacherDTO> addSubjectToTeacher(@RequestBody AddSubjectToTeacherRequest request) {
        return new ResponseEntity(teacherService.addSubjectToTeacher(request.getTeacherId(), request.getSubjectId()), HttpStatus.CREATED);
    }

    @PostMapping(
            path = "/teacher/dropSubject"
    )
    public ResponseEntity<TeacherDTO> dropSubjectFromTeacher(@RequestBody AddSubjectToTeacherRequest request) {
        return new ResponseEntity(teacherService.dropSubjectFromTeacher(request.getTeacherId(), request.getSubjectId()), HttpStatus.OK);
    }
}
