package com.ystan.schedule.handlers;

import com.ystan.schedule.enums.Day;
import com.ystan.schedule.handlers.common.BaseScheduleGenerationHandler;
import com.ystan.schedule.handlers.common.ScheduleGenerationRequest;
import com.ystan.schedule.models.*;
import com.ystan.schedule.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.ystan.schedule.config.ConfigContainer.COMMON_DAY_CAPACITY;
import static com.ystan.schedule.config.ConfigContainer.MAX_DAY_CAPACITY;

@Component
public class TimeSlotPopulationHandler extends BaseScheduleGenerationHandler {

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public void handle(ScheduleGenerationRequest request) {
        final Rule rule = request.getRule();
        final Group group = rule.getGroup();
        final Subject subject = rule.getSubject();
        final Teacher teacher = request.getSelectedTeacher();
        final Classroom classroom = request.getSelectedClassroom();

        populateList(request, rule.getTimesPerWeek());

        for (Lesson lesson : request.getLessons()) {
            boolean setDay = setDayOfWeek(lesson, subject, group, teacher, classroom, COMMON_DAY_CAPACITY);
            if (!setDay) {
                setDayOfWeek(lesson, subject, group, teacher, classroom, MAX_DAY_CAPACITY);
            }
            lesson = lessonRepository.save(lesson);
        }

        next(request);
    }

    private boolean setDayOfWeek(Lesson lesson, Subject subject, Group group, Teacher teacher, Classroom classroom, Integer dayCapacity) {
        for (Day day : Day.values()) {
            if (worksForGroup(group, subject, day, dayCapacity)
                    && worksForTeacher(teacher, day, dayCapacity)
                    && worksForClassroom(classroom, day, dayCapacity)
            ) {
                Set<Integer> freeOrdinalsForGroup = getFreeOrdinalsForGroup(group, day, dayCapacity);
                Set<Integer> freeOrdinalsForTeacher = getFreeOrdinalsForTeacher(teacher, day, dayCapacity);
                Set<Integer> freeOrdinalsForClassroom = getFreeOrdinalsForClassroom(classroom, day, dayCapacity);

                Set<Integer> groupTeacherIntersection = getRangeIntersection(freeOrdinalsForGroup, freeOrdinalsForTeacher);
                Set<Integer> groupTeacherClassroomIntersection = getRangeIntersection(groupTeacherIntersection, freeOrdinalsForClassroom);

                if (!groupTeacherClassroomIntersection.isEmpty()) {
                    Integer firstFree = groupTeacherClassroomIntersection.stream()
                            .min(Integer::compareTo)
                            .get();
                    lesson.setOrdinalNumber(firstFree);
                    lesson.setDayOfWeek(day);

                    return true;
                }
            }
        }

        return false;
    }

    private Set<Integer> getRange(Integer dayCapacity) {
        return IntStream
                .rangeClosed(0, dayCapacity)
                .boxed()
                .collect(Collectors.toSet());
    }

    private Set<Integer> getRangeIntersection(Set<Integer> set1, Set<Integer> set2) {
        return set1.stream()
                .distinct()
                .filter(set2::contains)
                .collect(Collectors.toSet());
    }

    private Set<Integer> getFreeOrdinalsForGroup(Group group, Day day, Integer dayCapacity) {
        Set<Integer> takenRange = lessonRepository.findByGroupIdAndDayOfWeek(group.getId(), day).stream()
                .map(Lesson::getOrdinalNumber)
                .collect(Collectors.toSet());

        return getFreeRange(takenRange, dayCapacity);
    }

    private Set<Integer> getFreeOrdinalsForTeacher(Teacher teacher, Day day, Integer dayCapacity) {
        Set<Integer> takenRange = lessonRepository.findByTeacherIdAndDayOfWeek(teacher.getId(), day).stream()
                .map(Lesson::getOrdinalNumber)
                .collect(Collectors.toSet());

        return getFreeRange(takenRange, dayCapacity);
    }

    private Set<Integer> getFreeOrdinalsForClassroom(Classroom classroom, Day day, Integer dayCapacity) {
        Set<Integer> takenRange = lessonRepository.findByClassroomIdAndDayOfWeek(classroom.getId(), day).stream()
                .map(Lesson::getOrdinalNumber)
                .collect(Collectors.toSet());

        return getFreeRange(takenRange, dayCapacity);
    }

    private Set<Integer> getFreeRange(Set<Integer> takenRange, Integer capacity) {
        Set<Integer> wholeRange = getRange(capacity);
        wholeRange.remove(0);
        Set<Integer> freeRange = wholeRange.stream()
                .filter(el -> !takenRange.contains(el))
                .collect(Collectors.toSet());

        return freeRange;
    }

    private boolean worksForGroup(Group group, Subject subject, Day day, Integer dayCapacity) {
        boolean works = false;

        List<Lesson> lessons = lessonRepository.findByGroupIdAndDayOfWeek(group.getId(), day);
        if(lessons.stream().anyMatch(lesson -> lesson.getSubject().getId() == subject.getId())) {
            return false;
        }
        if (lessons.size() < dayCapacity) {
            works = true;
        }

        return works;
    }

    private boolean worksForTeacher(Teacher teacher, Day day, Integer dayCapacity) {
        boolean works = false;

        List<Lesson> lessons = lessonRepository.findByTeacherIdAndDayOfWeek(teacher.getId(), day);
        if (lessons.size() < dayCapacity) {
            works = true;
        }

        return works;
    }

    private boolean worksForClassroom(Classroom classroom, Day day, Integer dayCapacity) {
        boolean works = false;

        List<Lesson> lessons = lessonRepository.findByClassroomIdAndDayOfWeek(classroom.getId(), day);
        if (lessons.size() < dayCapacity) {
            works = true;
        }

        return works;
    }

    private void populateList(ScheduleGenerationRequest request, Integer number) {
        if (request.getLessons() == null) {
            request.setLessons(new ArrayList<Lesson>(number));
        }
    }
}
