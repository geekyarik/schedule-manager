package com.ystan.schedule.requests;

import com.ystan.schedule.enums.Day;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAvailableRequest {
    private Day day;
    private Integer ordinalNumber;
    private String schoolId;
    private String subjectId;
}
