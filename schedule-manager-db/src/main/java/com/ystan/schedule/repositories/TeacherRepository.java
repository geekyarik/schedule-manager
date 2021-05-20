package com.ystan.schedule.repositories;

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
}
