package com.ystan.schedule.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SM_RULE")
public class Rule {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private Integer timesPerWeek;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "groupId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_RULE_TO_GROUP")
    )
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "subjectId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_RULE_TO_SUBJECT")
    )
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "classroomId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_RULE_TO_CLASSROOM")
    )
    private Classroom preferredRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "teacherId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_RULE_TO_TEACHER")
    )
    private Teacher preferredTeacher;
}
