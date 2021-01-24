package com.ystan.schedule.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static com.ystan.schedule.models.Classroom.TABLE_NAME;

@Data
@Entity
@Table(name = TABLE_NAME)
public class Classroom {

    public static final String TABLE_NAME = "SM_CLASSROOM";

    @Id
    private String id;

    @Column(unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "schoolId",
            referencedColumnName = "id"
    )
    private School school;

    @JsonIgnore
    @OneToMany(
            mappedBy = "classroom",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    private List<Lesson> lessons;
}
