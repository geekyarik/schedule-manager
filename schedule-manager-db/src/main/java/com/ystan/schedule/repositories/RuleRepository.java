package com.ystan.schedule.repositories;

import com.ystan.schedule.models.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RuleRepository extends JpaRepository<Rule, String> {

    @Query(
            value = "SELECT * " +
                    "FROM sm_rule r " +
                    "LEFT JOIN sm_group g on g.id = r.group_id " +
                    "WHERE g.school_id = ?1",
            nativeQuery = true
    )
    List<Rule> findBySchoolId(String id);
}
