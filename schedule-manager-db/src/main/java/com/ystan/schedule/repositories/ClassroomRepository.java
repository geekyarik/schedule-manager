package com.ystan.schedule.repositories;

import com.ystan.schedule.models.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

//@Repository
@Transactional
public interface ClassroomRepository extends JpaRepository<Classroom, String> {
}
