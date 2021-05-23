package com.ystan.schedule.comparators;

import com.ystan.schedule.models.Teacher;
import com.ystan.schedule.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class TeacherByLessonsCountComparator implements Comparator<Teacher> {

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public int compare(Teacher o1, Teacher o2) {
        return Integer.compare(
                lessonRepository.countByTeacher(o1.getId()),
                lessonRepository.countByTeacher(o2.getId())
        );
    }
}
