package com.ystan.schedule.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBySchoolIdRequest {
    private String schoolId;
}
