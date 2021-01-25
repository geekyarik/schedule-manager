package com.ystan.schedule.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.ystan.schedule.models.Lesson.TABLE_NAME;

@Data
@Entity
@Table(name = TABLE_NAME)
public class Lesson {

    public static final String TABLE_NAME = "SM_LESSON";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column
    private String name;

    @Column
    private LocalDateTime startTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "teacherId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_LESSON_TO_TEACHER")

    )
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "classroomId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_LESSON_TO_CLASSROOM")
    )
    private Classroom classroom;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "groupId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_LESSON_TO_GROUP")
    )
    private Group group;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "subjectId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_LESSON_TO_SUBJECT")
    )
    private Subject subject;
}
