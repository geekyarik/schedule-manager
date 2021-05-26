package com.ystan.schedule.controllers;

import com.ystan.schedule.models.ClassroomDTO;
import com.ystan.schedule.requests.GetAvailableRequest;
import com.ystan.schedule.services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http:")
@RestController
@RequestMapping("schedule")
public class ClassroomControllerImpl {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomControllerImpl(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping(
            path = "/classroom/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ClassroomDTO> getById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(classroomService.findById(id), HttpStatus.OK);
    }

    @GetMapping(
            path = "/classroom",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ClassroomDTO>> getBySchoolId(@RequestParam(name = "schoolId") String schoolId) {
        return new ResponseEntity<>(classroomService.findBySchoolId(schoolId), HttpStatus.OK);
    }

    @PostMapping(
            path = "/classroom/available",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ClassroomDTO>> getAvailable(@RequestBody GetAvailableRequest request) {
        return new ResponseEntity<>(classroomService.findAvailable(request.getDay(), request.getOrdinalNumber(), request.getSchoolId()), HttpStatus.OK);
    }

    @PostMapping(
            path = "/classroom"
    )
    public ResponseEntity<ClassroomDTO> saveOrUpdate(@RequestBody ClassroomDTO classroom) {
        return new ResponseEntity<>(classroomService.saveOrUpdate(classroom), HttpStatus.CREATED);
    }

    @DeleteMapping(
            path = "/classroom/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ClassroomDTO> deleteById(@PathVariable(name = "id") String id) {
        return new ResponseEntity(classroomService.delete(id), HttpStatus.OK);
    }
}
