package com.ystan.schedule.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

import static com.ystan.schedule.models.Group.TABLE_NAME;

@Data
@Entity
@Table(name = TABLE_NAME)
public class Group {

    public static final String TABLE_NAME = "SM_GROUP";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "schoolId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_GROUP_TO_SCHOOL")
    )
    private School school;

    @OneToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "scheduleId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_GROUP_TO_SCHEDULE")
    )
    private Schedule schedule;

    @OneToMany(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            mappedBy = "group"
    )
    @JsonIgnore
    private List<Lesson> lessons;

    @OneToMany(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            mappedBy = "group"
    )
    @JsonIgnore
    private List<Rule> rules;
}
