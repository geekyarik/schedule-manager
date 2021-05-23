package com.ystan.schedule.handlers.common;

import lombok.Setter;

public abstract class BaseScheduleGenerationHandler implements ScheduleGenerationHandler {

    @Setter
    protected ScheduleGenerationHandler next;

    protected void next(ScheduleGenerationRequest request) {
        if (next != null) {
            next.handle(request);
        }
    }
}
