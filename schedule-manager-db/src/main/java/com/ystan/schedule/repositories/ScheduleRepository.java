package com.ystan.schedule.repositories;

import com.ystan.schedule.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, String> {
}
