package com.ystan.schedule.models;

import com.ystan.schedule.models.common.EntityDTO;
import lombok.Data;

@Data
public class SchoolDTO extends EntityDTO {

    private String name;

    private String address;
}
