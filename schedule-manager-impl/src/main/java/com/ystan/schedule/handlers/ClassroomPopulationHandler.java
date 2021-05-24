package com.ystan.schedule.handlers;

import com.ystan.schedule.comparators.ClassroomByLessonsCountComparator;
import com.ystan.schedule.handlers.common.BaseScheduleGenerationHandler;
import com.ystan.schedule.handlers.common.ScheduleGenerationRequest;
import com.ystan.schedule.models.Classroom;
import com.ystan.schedule.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class ClassroomPopulationHandler extends BaseScheduleGenerationHandler {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Override
    public void handle(ScheduleGenerationRequest request) {
        Classroom selectedClassroom = selectClassroom(request);
        request.setSelectedClassroom(selectedClassroom);

        request.getLessons().forEach(lesson -> lesson.setClassroom(selectedClassroom));

        next(request);
    }

    private Classroom selectClassroom(ScheduleGenerationRequest request) {
        final Comparator<Classroom> comparator = new ClassroomByLessonsCountComparator();
        final Classroom preferredRoom = request.getRule().getPreferredRoom();
        Classroom selectedClassroom = preferredRoom;

        if (preferredRoom == null) {
            List<Classroom> classrooms = classroomRepository.findBySchoolId(request.getSchoolId());
            selectedClassroom = classrooms.stream()
                    .min(comparator)
                    .get();
        }

        return selectedClassroom;
    }
}
