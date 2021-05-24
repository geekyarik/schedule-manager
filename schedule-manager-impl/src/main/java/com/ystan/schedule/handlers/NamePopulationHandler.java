package com.ystan.schedule.handlers;

import com.ystan.schedule.handlers.common.BaseScheduleGenerationHandler;
import com.ystan.schedule.handlers.common.ScheduleGenerationRequest;
import com.ystan.schedule.models.Lesson;
import com.ystan.schedule.models.Rule;
import com.ystan.schedule.models.Subject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class NamePopulationHandler extends BaseScheduleGenerationHandler {
    
    @Override
    public void handle(ScheduleGenerationRequest request) {
        final Rule rule = request.getRule();
        final String name = generateName(rule);

        populateList(request, rule.getTimesPerWeek());

        request.getLessons().forEach(lesson -> lesson.setName(name));

        next(request);
    }

    private String generateName(Rule rule) {
        return rule.getSubject().getName();
    }

    private void populateList(ScheduleGenerationRequest request, Integer number) {
        if (request.getLessons() == null) {
            request.setLessons(new ArrayList<Lesson>());
        }
        for(int i = 0; i < number; i++) {
            request.getLessons().add(new Lesson());
        }
    }
}
