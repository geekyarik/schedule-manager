package com.ystan.schedule.handlers;

import com.ystan.schedule.handlers.common.BaseScheduleGenerationHandler;
import com.ystan.schedule.handlers.common.ScheduleGenerationRequest;
import com.ystan.schedule.models.Group;
import com.ystan.schedule.models.Rule;
import com.ystan.schedule.models.Subject;

public class SubjectPopulationHandler extends BaseScheduleGenerationHandler {
    
    @Override
    public void handle(ScheduleGenerationRequest request) {
        final Rule rule = request.getRule();
        final Subject subject = rule.getSubject();

        request.getLessons().forEach(lesson -> lesson.setSubject(subject));

        next(request);
    }
}
