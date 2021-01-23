package com.ystan.schedule.repositories;

import com.ystan.schedule.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GroupRepository extends JpaRepository<Group, String> {
}
