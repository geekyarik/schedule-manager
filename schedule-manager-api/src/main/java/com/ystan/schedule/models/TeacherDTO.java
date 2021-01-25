package com.ystan.schedule.models;

import com.ystan.schedule.models.common.EntityDTO;
import lombok.Data;

import javax.persistence.Column;

@Data
public class TeacherDTO extends EntityDTO {

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;
}
