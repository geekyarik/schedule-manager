package com.ystan.schedule.handlers.common;

import com.ystan.schedule.repositories.LessonRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseScheduleGenerationHandler implements ScheduleGenerationHandler {

    @Autowired
    private LessonRepository lessonRepository;

    @Setter
    protected ScheduleGenerationHandler next;

    protected void next(ScheduleGenerationRequest request) {
        if (next != null) {
            next.handle(request);
        } else {
            request.getLessons().forEach(lessonRepository::save);
        }
    }
}
