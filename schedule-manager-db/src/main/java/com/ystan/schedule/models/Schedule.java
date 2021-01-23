package com.ystan.schedule.models;

import lombok.Data;

import javax.persistence.*;

import static com.ystan.schedule.models.Schedule.TABLE_NAME;

@Data
@Table(name = TABLE_NAME)
@Entity
public class Schedule {

    public static final String TABLE_NAME = "SM_SCHEDULE";

    @Id
    private String id;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            name = "groupId",
            referencedColumnName = "id"
    )
    private Group group;
}
