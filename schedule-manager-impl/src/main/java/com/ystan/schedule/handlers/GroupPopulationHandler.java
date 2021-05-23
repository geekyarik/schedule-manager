package com.ystan.schedule.handlers;

import com.ystan.schedule.handlers.common.BaseScheduleGenerationHandler;
import com.ystan.schedule.handlers.common.ScheduleGenerationRequest;
import com.ystan.schedule.models.Group;
import com.ystan.schedule.models.Rule;
import com.ystan.schedule.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupPopulationHandler extends BaseScheduleGenerationHandler {

    @Override
    public void handle(ScheduleGenerationRequest request) {
        final Rule rule = request.getRule();
        final Group group = rule.getGroup();

        request.getLessons().forEach(lesson -> lesson.setGroup(group));

        next(request);
    }
}
