package com.ystan.schedule.repositories;

import com.ystan.schedule.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SubjectRepository extends JpaRepository<Subject, String> {

    @Query(
            value = "INSERT INTO sm_subj_teach (teacher_id, subject_id) " +
                    "VALUES (?1, ?2) " +
                    "RETURNING subject_id",
            nativeQuery = true
    )
    String addSubjectToTeacher(String teacherId, String subjectId);

    @Modifying
    @Query(
            value = "DELETE FROM sm_subj_teach " +
                    "WHERE  teacher_id = ?1 AND subject_id = ?2",
            nativeQuery = true
    )
    void dropSubjectFromTeacher(String teacherId, String subjectId);

    @Query(
            value = "SELECT s.* FROM sm_subject s " +
                    "LEFT JOIN sm_subj_teach st on s.id = st.subject_id " +
                    "WHERE teacher_id = ?1",
            nativeQuery = true
    )
    List<Subject> findByTeacherId(String teacherId);
}
