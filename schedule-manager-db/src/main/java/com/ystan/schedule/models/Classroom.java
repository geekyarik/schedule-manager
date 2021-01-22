package com.ystan.schedule.models;

import lombok.Data;

import javax.persistence.*;

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
}
