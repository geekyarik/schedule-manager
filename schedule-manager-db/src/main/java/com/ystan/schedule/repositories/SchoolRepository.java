package com.ystan.schedule.repositories;

import com.ystan.schedule.models.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SchoolRepository extends JpaRepository<School, String> {
}
