package com.ystan.schedule.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

import static com.ystan.schedule.models.Subject.TABLE_NAME;

@Data
@Entity
@Table(name = TABLE_NAME)
public class Subject {

    public static final String TABLE_NAME = "SM_SUBJECT";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "SM_SUBJ_TEACH",
            joinColumns = @JoinColumn(name = "subjectId"),
            inverseJoinColumns = @JoinColumn(name = "teacherId"),
            uniqueConstraints = @UniqueConstraint(name = "UNIQUE_SUBJECTS_FOR_TEACHER", columnNames = {"subjectId","teacherId"})
    )
    @JsonIgnore
    private List<Teacher> teachers;

    @OneToMany(
            mappedBy = "subject",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JsonIgnore
    private List<Lesson> lessons;

    @OneToMany(
            mappedBy = "subject",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JsonIgnore
    private List<Rule> rules;
}
