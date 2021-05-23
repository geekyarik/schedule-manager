package com.ystan.schedule.repositories;

import com.ystan.schedule.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface LessonRepository extends JpaRepository<Lesson, String> {

    List<Lesson> findByGroupId(String schoolId);

    List<Lesson> findByTeacherId(String schoolId);

    List<Lesson> findByClassroomId(String schoolId);

    List<Lesson> findByGroupAndDayOfWeek(String groupId, String dayOfWeek);

    List<Lesson> findByTeacherAndDayOfWeek(String teacherId, String dayOfWeek);

    List<Lesson> findByClassroomAndDayOfWeek(String classroomId, String dayOfWeek);

    Integer countByTeacher(String teacherId);

    Integer countByClassroom(String classroomId);
}
