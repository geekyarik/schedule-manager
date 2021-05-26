package com.ystan.schedule.controllers;

import com.ystan.schedule.models.ClassroomDTO;
import com.ystan.schedule.models.GroupDTO;
import com.ystan.schedule.models.TeacherDTO;
import com.ystan.schedule.services.ClassroomService;
import com.ystan.schedule.services.GroupService;
import com.ystan.schedule.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("available")
public class AvailableControllerImpl {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private GroupService groupService;

    @PostMapping(
            path = "/classroom",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ClassroomDTO>> getClassroomBySchoolId(@RequestParam(name = "schoolId") String schoolId) {
        return new ResponseEntity<>(classroomService.findBySchoolId(schoolId), HttpStatus.OK);
    }


    @PostMapping(
            path = "/teacher",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TeacherDTO>> getTeacherBySchoolId(@RequestParam(name = "schoolId") String schoolId) {
        return new ResponseEntity<>(teacherService.findBySchoolId(schoolId), HttpStatus.OK);
    }


    @PostMapping(
            path = "/group",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GroupDTO>> getBySchoolId(@RequestParam(name = "schoolId") String schoolId) {
        return new ResponseEntity<>(groupService.findBySchoolId(schoolId), HttpStatus.OK);
    }
}
