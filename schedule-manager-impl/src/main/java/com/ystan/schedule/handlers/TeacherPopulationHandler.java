package com.ystan.schedule.handlers;

import com.ystan.schedule.comparators.TeacherByLessonsCountComparator;
import com.ystan.schedule.handlers.common.BaseScheduleGenerationHandler;
import com.ystan.schedule.handlers.common.ScheduleGenerationRequest;
import com.ystan.schedule.models.Subject;
import com.ystan.schedule.models.Teacher;
import com.ystan.schedule.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class TeacherPopulationHandler extends BaseScheduleGenerationHandler {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void handle(ScheduleGenerationRequest request) {
        Teacher selectedTeacher = selectTeacher(request);
        request.setSelectedTeacher(selectedTeacher);

        request.getLessons().forEach(lesson -> lesson.setTeacher(selectedTeacher));

        next(request);
    }


    private Teacher selectTeacher(ScheduleGenerationRequest request) {
        final Subject subject = request.getRule().getSubject();
        final Teacher preferredTeacher = request.getRule().getPreferredTeacher();
        Teacher selectedTeacher = preferredTeacher;

        if (preferredTeacher == null) {
            final Comparator<Teacher> comparator = new TeacherByLessonsCountComparator();
            final List<Teacher> teachers = teacherRepository.findBySubjectIdAndSchoolId(subject.getId(), request.getSchoolId());

            selectedTeacher = teachers.stream()
                    .min(comparator)
                    .get();
            request.setSelectedTeacher(selectedTeacher);
        }

        return selectedTeacher;
    }
}
