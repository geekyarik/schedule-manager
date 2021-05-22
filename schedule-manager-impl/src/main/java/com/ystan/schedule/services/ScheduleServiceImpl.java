package com.ystan.schedule.services;

import com.ystan.schedule.models.Lesson;
import com.ystan.schedule.models.LessonDTO;
import com.ystan.schedule.models.RuleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private RuleService ruleService;

    @Autowired
    private LessonService lessonService;

    @Override
    public void generateSchedule(String schoolId) {
        List<RuleDTO> rules = ruleService.findBySchoolId(schoolId);


    }

    private Lesson generateLessonFromRule(RuleDTO rule) {
        return null;
    }
}
