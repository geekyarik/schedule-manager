package com.ystan.schedule.services;

import com.ystan.schedule.handlers.ClassroomPopulationHandler;
import com.ystan.schedule.handlers.TeacherPopulationHandler;
import com.ystan.schedule.handlers.TimeSlotPopulationHandler;
import com.ystan.schedule.handlers.common.ScheduleGenerationRequest;
import com.ystan.schedule.models.Lesson;
import com.ystan.schedule.models.Rule;
import com.ystan.schedule.repositories.LessonRepository;
import com.ystan.schedule.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public void generateSchedule(String schoolId) {
        List<Rule> rules = ruleRepository.findBySchoolId(schoolId);
        rules.stream()
                .map(rule -> generateLessonFromRule(rule, schoolId))
                .forEach(lessonRepository::save);
    }

    private Lesson generateLessonFromRule(Rule rule, String schoolId) {
        ScheduleGenerationRequest request = new ScheduleGenerationRequest();
        request.setRule(rule);
        request.setSchoolId(schoolId);

        ClassroomPopulationHandler classroomHandler = new ClassroomPopulationHandler();
        TeacherPopulationHandler teacherHandler = new TeacherPopulationHandler();
        TimeSlotPopulationHandler timeSlotHandler = new TimeSlotPopulationHandler();

        teacherHandler.setNext(timeSlotHandler);
        classroomHandler.setNext(teacherHandler);

        classroomHandler.handle(request);

        return null;
    }
}
