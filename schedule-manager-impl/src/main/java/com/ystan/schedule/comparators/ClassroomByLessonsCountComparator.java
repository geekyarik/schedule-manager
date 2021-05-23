package com.ystan.schedule.comparators;

import com.ystan.schedule.models.Classroom;
import com.ystan.schedule.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class ClassroomByLessonsCountComparator implements Comparator<Classroom> {

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public int compare(Classroom o1, Classroom o2) {
        return Integer.compare(
                lessonRepository.countByClassroom(o1.getId()),
                lessonRepository.countByClassroom(o2.getId())
        );
    }
}
