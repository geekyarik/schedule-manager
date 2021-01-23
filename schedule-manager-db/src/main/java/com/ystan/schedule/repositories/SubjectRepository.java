package com.ystan.schedule.repositories;

import com.ystan.schedule.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SubjectRepository extends JpaRepository<Subject, String> {
}
