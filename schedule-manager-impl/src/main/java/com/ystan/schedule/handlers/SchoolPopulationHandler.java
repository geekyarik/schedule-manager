package com.ystan.schedule.handlers;

import com.ystan.schedule.handlers.common.BaseScheduleGenerationHandler;
import com.ystan.schedule.handlers.common.ScheduleGenerationRequest;
import com.ystan.schedule.models.Rule;
import com.ystan.schedule.models.School;
import com.ystan.schedule.models.Subject;
import com.ystan.schedule.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchoolPopulationHandler extends BaseScheduleGenerationHandler {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public void handle(ScheduleGenerationRequest request) {
        final School school = schoolRepository.getOne(request.getSchoolId());

        request.getLessons().forEach(lesson -> lesson.setSchool(school));

        next(request);
    }
}
