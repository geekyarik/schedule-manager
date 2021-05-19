package com.ystan.schedule.controllers;


import com.ystan.schedule.models.ClassroomDTO;
import com.ystan.schedule.models.GroupDTO;
import com.ystan.schedule.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("schedule")
public class GroupControllerImpl {

    @Autowired
    private GroupService groupService;

    @GetMapping(
            path = "/group/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GroupDTO> getById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(groupService.findById(id), HttpStatus.OK);
    }

    @GetMapping(
            path = "/group",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GroupDTO>> getBySchoolId(@RequestParam(name = "schoolId") String schoolId) {
        return new ResponseEntity<>(groupService.findBySchoolId(schoolId), HttpStatus.OK);
    }

    @PostMapping(
            path = "/group"
    )
    public ResponseEntity<GroupDTO> saveOrUpdate(@RequestBody GroupDTO group) {
        return new ResponseEntity<>(groupService.saveOrUpdate(group), HttpStatus.CREATED);
    }

    @DeleteMapping(
            path = "/group/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ClassroomDTO> deleteById(@PathVariable(name = "id") String id) {
        return new ResponseEntity(groupService.delete(id), HttpStatus.OK);
    }
}
