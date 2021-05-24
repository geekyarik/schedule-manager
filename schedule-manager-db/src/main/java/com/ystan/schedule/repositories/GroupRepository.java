package com.ystan.schedule.repositories;

import com.ystan.schedule.models.Classroom;
import com.ystan.schedule.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface GroupRepository extends JpaRepository<Group, String> {

    List<Group> findBySchoolId(String schoolId);

    @Query(
            value = "SELECT grp.* " +
                    "FROM sm_group grp " +
                    "LEFT JOIN sm_lesson les on grp.id = les.group_id " +
                    "WHERE grp.school_id = ?3 AND les.dayOfWeek != ?1 and les.ordinalNumber != ?2",
            nativeQuery = true
    )
    List<Group> findAvailable(String day, Integer ordinalNumber, String schoolId);
}
