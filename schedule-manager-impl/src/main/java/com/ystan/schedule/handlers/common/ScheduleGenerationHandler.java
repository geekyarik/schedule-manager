package com.ystan.schedule.handlers.common;

public interface ScheduleGenerationHandler {

    void setNext(ScheduleGenerationHandler handler);

    void handle(ScheduleGenerationRequest request);
}
