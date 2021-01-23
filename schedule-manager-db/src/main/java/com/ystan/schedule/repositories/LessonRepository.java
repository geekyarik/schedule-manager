package com.ystan.schedule.repositories;

import com.ystan.schedule.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LessonRepository extends JpaRepository<Lesson, String> {
}
