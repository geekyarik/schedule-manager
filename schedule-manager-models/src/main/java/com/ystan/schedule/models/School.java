package com.ystan.schedule.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

import static com.ystan.schedule.models.School.TABLE_NAME;

@Data
@Entity
@Table(name = TABLE_NAME)
public class School {

    public static final String TABLE_NAME = "SM_SCHOOL";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(unique = true)
    private String name;

    @Column
    private String address;

    @OneToMany(
            mappedBy = "school",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JsonIgnore
    private List<Classroom> classrooms;

    @OneToMany(
            mappedBy = "school",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JsonIgnore
    private List<Teacher> teachers;

    @OneToMany(
            mappedBy = "school",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JsonIgnore
    private List<Group> groups;
}
