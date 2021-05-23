package com.ystan.schedule.handlers;

import com.ystan.schedule.handlers.common.BaseScheduleGenerationHandler;
import com.ystan.schedule.handlers.common.ScheduleGenerationRequest;
import com.ystan.schedule.models.Rule;
import com.ystan.schedule.models.Subject;

public class NamePopulationHandler extends BaseScheduleGenerationHandler {
    
    @Override
    public void handle(ScheduleGenerationRequest request) {
        final Rule rule = request.getRule();
        final String name = generateName(rule);

        request.getLessons().forEach(lesson -> lesson.setName(name));

        next(request);
    }

    private String generateName(Rule rule) {
        return rule.getSubject().getName();
    }
}
