package com.ystan.schedule.repositories;

import com.ystan.schedule.enums.Day;
import com.ystan.schedule.models.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ClassroomRepository extends JpaRepository<Classroom, String> {

    List<Classroom> findBySchoolId(String schoolId);

    @Query(
            value = "SELECT cl.* " +
                    "FROM sm_classroom cl " +
                    "LEFT JOIN sm_lesson sl on cl.id = sl.classroom_id " +
                    "LEFT JOIN sm_group sg on sl.group_id = sg.id " +
                    "WHERE sg.school_id = ?3 AND sl.dayOfWeek != ?1 and sl.ordinalNumber != ?2",
            nativeQuery = true
    )
    List<Classroom> findAvailable(String day, Integer ordinalNumber, String schoolId);
}
