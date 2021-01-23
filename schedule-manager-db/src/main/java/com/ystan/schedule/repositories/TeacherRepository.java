package com.ystan.schedule.repositories;

import com.ystan.schedule.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TeacherRepository extends JpaRepository<Teacher, String> {
}
