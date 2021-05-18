package com.ystan.schedule.models;

import com.ystan.schedule.models.common.EntityDTO;
import lombok.Data;

@Data
public class ClassroomDTO extends EntityDTO {

    private String id;

    private String name;

    private String schoolId;
}
