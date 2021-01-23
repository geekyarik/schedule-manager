package com.ystan.schedule.models;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.ystan.schedule.models.Lesson.TABLE_NAME;

@Data
@Entity
@Table(name = TABLE_NAME)
public class Lesson {

    public static final String TABLE_NAME = "SM_LESSON";

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private LocalDateTime startTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "teacherId",
            referencedColumnName = "id"
    )
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "classroomId",
            referencedColumnName = "id"
    )
    private Classroom classroom;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "groupId",
            referencedColumnName = "id"
    )
    private Group group;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "subjectId",
            referencedColumnName = "id"
    )
    private Subject subject;
}
