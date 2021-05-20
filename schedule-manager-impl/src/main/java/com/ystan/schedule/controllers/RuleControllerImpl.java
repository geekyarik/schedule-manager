package com.ystan.schedule.controllers;


import com.ystan.schedule.models.ClassroomDTO;
import com.ystan.schedule.models.GroupDTO;
import com.ystan.schedule.models.RuleDTO;
import com.ystan.schedule.services.GroupService;
import com.ystan.schedule.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("schedule")
public class RuleControllerImpl {

    @Autowired
    private RuleService ruleService;

    @GetMapping(
            path = "/rule/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RuleDTO> getById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(ruleService.findById(id), HttpStatus.OK);
    }

    @GetMapping(
            path = "/rule",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<RuleDTO>> getBySchoolId(@RequestParam(name = "schoolId") String schoolId) {
        return new ResponseEntity(ruleService.findBySchoolId(schoolId), HttpStatus.OK);
    }

    @PostMapping(
            path = "/rule"
    )
    public ResponseEntity<RuleDTO> saveOrUpdate(@RequestBody RuleDTO rule) {
        return new ResponseEntity<>(ruleService.saveOrUpdate(rule), HttpStatus.CREATED);
    }

    @PostMapping(
            path = "/rule/{id}/delete",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RuleDTO> deleteById(@PathVariable(name = "id") String id) {
        return new ResponseEntity(ruleService.delete(id), HttpStatus.OK);
    }
}
