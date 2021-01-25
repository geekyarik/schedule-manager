package com.ystan.schedule.repositories;

import com.ystan.schedule.models.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RuleRepository extends JpaRepository<Rule, String> {
}
