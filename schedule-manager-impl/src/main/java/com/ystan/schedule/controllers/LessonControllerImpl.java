package com.ystan.schedule.controllers;


import com.ystan.schedule.models.LessonDTO;
import com.ystan.schedule.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("schedule")
public class LessonControllerImpl {

    @Autowired
    private LessonService lessonService;

    @GetMapping(
            path = "/lesson/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LessonDTO> getById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(lessonService.findById(id), HttpStatus.OK);
    }

    @PostMapping(
            path = "/lesson/{id}/delete",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LessonDTO> deleteById(@PathVariable(name = "id") String id) {
        lessonService.deleteById(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @GetMapping(
            path = "/lesson/classroom/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<LessonDTO>> getByClassroomId(@PathVariable(name = "id") String classroomId) {
        return new ResponseEntity<>(lessonService.findByClassroomId(classroomId), HttpStatus.OK);
    }

    @GetMapping(
            path = "/lesson/group/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<LessonDTO>> getByGroupId(@PathVariable(name = "id") String groupId) {
        return new ResponseEntity<>(lessonService.findByGroupId(groupId), HttpStatus.OK);
    }

    @GetMapping(
            path = "/lesson/teacher/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<LessonDTO>> getByTeacherId(@PathVariable(name = "id") String teacherId) {
        return new ResponseEntity<>(lessonService.findByTeacherId(teacherId), HttpStatus.OK);
    }

    @PostMapping(
            path = "/lesson"
    )
    public ResponseEntity<LessonDTO> saveOrUpdate(@RequestBody LessonDTO lesson) {
        return new ResponseEntity<>(lessonService.saveOrUpdate(lesson), HttpStatus.CREATED);
    }
}
