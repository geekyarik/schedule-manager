package com.ystan.schedule.repositories;

import com.ystan.schedule.models.Group;
import com.ystan.schedule.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    Teacher findByEmail(String email);

    boolean existsByEmail(String email);

    @Query(
            value = "SELECT * " +
                    "FROM sm_teacher " +
                    "WHERE school_id = ?1",
            nativeQuery = true
    )
    List<Teacher> findBySchoolId(String schoolId);

    @Query(
            value = "SELECT * " +
                    "FROM sm_teacher t " +
                    "LEFT JOIN sm_subj_teach sst on t.id = sst.teacher_id " +
                    "WHERE sst.subject_id = ?1",
            nativeQuery = true
    )
    List<Teacher> findBySubjectId(String schoolId);

    @Query(
            value = "SELECT * " +
                    "FROM sm_teacher t " +
                    "LEFT JOIN sm_subj_teach sst on t.id = sst.teacher_id " +
                    "WHERE sst.subject_id = ?1 AND t.school_id = ?2",
            nativeQuery = true
    )
    List<Teacher> findBySubjectIdAndSchoolId(String subjectId, String schoolId);


    @Query(
            value = "SELECT tch.* " +
                    "FROM sm_teacher tch " +
                    "LEFT JOIN sm_lesson les on tch.id = les.teacher_id " +
                    "LEFT JOIN sm_subj_teach sst on tch.id = sst.teacher_id " +
                    "WHERE tch.school_id = ?3 AND sst.subject_id = ?4 AND les.dayOfWeek != ?1 and les.ordinalNumber != ?2",
            nativeQuery = true
    )
    List<Teacher> findAvailable(String day, Integer ordinalNumber, String schoolId, String subjectId);
}
