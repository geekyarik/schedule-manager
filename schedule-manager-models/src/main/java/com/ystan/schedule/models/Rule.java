package com.ystan.schedule.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "SM_RULE")
public class Rule {

    @Id
    private String id;
}
