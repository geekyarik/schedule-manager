package com.ystan.schedule.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static com.ystan.schedule.models.Teacher.TABLE_NAME;

@Data
@Entity
@Table(name = TABLE_NAME)
public class Teacher {

    public static final String TABLE_NAME = "SM_TEACHER";

    @Id
    private String id;

    @Column
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(
            name = "schoolId",
            referencedColumnName = "id"
    )
    private School school;

    @ManyToMany(mappedBy = "teachers")
    @JsonIgnore
    private List<Subject> subjects;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "teacher",
            cascade = CascadeType.PERSIST
    )
    @JsonIgnore
    private List<Lesson> lessons;
}
